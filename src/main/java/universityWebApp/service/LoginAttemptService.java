package universityWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import universityWebApp.exception.IpNotFoundException;
import universityWebApp.model.Blacklist;
import universityWebApp.repository.BlacklistRepository;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


@Service
public class LoginAttemptService {

    @Autowired
    BlacklistRepository blacklistRepository;

    @Autowired
    private HttpServletRequest request;

    public LoginAttemptService() {
        super();
    }

    public void loginSucceeded(String key) {
        Blacklist black;
        try {
            black = blacklistRepository.findById(getIP()).orElseThrow(IpNotFoundException::new);
            black.setAttempts(0);
        }
        catch(IpNotFoundException i){
            black = new Blacklist(getIP(), 0);
            blacklistRepository.save(black);
        }
    }

    public void loginFailed(String key) {
        int attempts = 0;
        Blacklist black;
        try{
            black =  blacklistRepository.findById(getIP()).orElseThrow(IpNotFoundException::new);
            attempts =black.getAttempts();
            black.setAttempts(attempts+1);
            blacklistRepository.save(black);
        }
        catch(IpNotFoundException i) {
            black = new Blacklist(getIP(), 1);
            blacklistRepository.save(black);
        }

    }

    public boolean isBlocked(String key) {
        try {
            Blacklist black =  blacklistRepository.findById(getIP()).orElseThrow(IpNotFoundException::new);
            return black.getAttempts() >= 3;
        } catch (IpNotFoundException e) {
            return false;
        }
    }

    public String getIP(){
        if(request.getRemoteAddr().equalsIgnoreCase("0:0:0:0:0:0:0:1") || request.getRemoteAddr().equalsIgnoreCase("127.0.0.1")){
            try {
                return InetAddress.getLocalHost().getHostAddress();
            }
            catch (UnknownHostException e) {
                return null;
            }
        }

        return request.getRemoteAddr();
    }

}