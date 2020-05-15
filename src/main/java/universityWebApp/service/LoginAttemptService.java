package universityWebApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import universityWebApp.controller.FeesController;
import universityWebApp.exception.IPNotFoundException;
import universityWebApp.model.Blacklist;
import universityWebApp.repository.BlacklistRepository;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;


@Service
public class LoginAttemptService {

    @Autowired
    BlacklistRepository blacklistRepository;

    @Autowired
    private HttpServletRequest request;

    Logger logger = LoggerFactory.getLogger(LoginAttemptService.class);

    public void loginSucceeded() {
        Optional<Blacklist> blacklistOptional = blacklistRepository.findById(getIP());
        if (blacklistOptional.isPresent()) {
            Blacklist blacklist = blacklistOptional.get();
            blacklistRepository.delete(blacklist);
        }
    }

    public void loginFailed() {
        Optional<Blacklist> blacklistOptional = blacklistRepository.findById(getIP());
        if (blacklistOptional.isPresent()) {
            Blacklist blacklist = blacklistOptional.get();
            blacklist.setAttempts(blacklist.getAttempts() + 1);
            blacklistRepository.save(blacklist);
        } else {
            blacklistRepository.save(new Blacklist(getIP(), 1));
        }
    }

    public boolean isBlocked() {
        try {
            Blacklist black = blacklistRepository.findById(getIP()).orElseThrow(IPNotFoundException::new);
            return black.getAttempts() >=3;
        } catch (IPNotFoundException e) {
            return false;
        }
    }

    public String getIP() {
        if (request.getRemoteAddr().equalsIgnoreCase("0:0:0:0:0:0:0:1") || request.getRemoteAddr().equalsIgnoreCase("127.0.0.1")) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                return null;
            }
        }

        return request.getRemoteAddr();
    }

}