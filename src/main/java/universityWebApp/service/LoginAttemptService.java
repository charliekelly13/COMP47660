package universityWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import universityWebApp.exception.IpNotFoundException;
import universityWebApp.model.Blacklist;
import universityWebApp.repository.BlacklistRepository;

@Service
public class LoginAttemptService {

    @Autowired
    BlacklistRepository blacklistRepository;

    public LoginAttemptService() {
        super();
    }

    public void loginSucceeded(String key) {
        Blacklist black;
        try {
            black = blacklistRepository.findById(key).orElseThrow(IpNotFoundException::new);
            black.setAttempts(0);
        }
        catch(IpNotFoundException i){
            black = new Blacklist(key, 0);
            blacklistRepository.save(black);

        }
    }

    public void loginFailed(String key) {
        int attempts = 0;
        Blacklist black;
        try{
            black =  blacklistRepository.findById(key).orElseThrow(IpNotFoundException::new);
            attempts =black.getAttempts();
            black.setAttempts(attempts++);
        }
        catch(IpNotFoundException i) {
            black = new Blacklist(key, 1);
            blacklistRepository.save(black);
        }

    }

    public boolean isBlocked(String key) {
        try {
            Blacklist black =  blacklistRepository.findById(key).orElseThrow(IpNotFoundException::new);
            int MAX_ATTEMPT = 3;
            return black.getAttempts() >= MAX_ATTEMPT;
        } catch (IpNotFoundException e) {
            return false;
        }
    }


}