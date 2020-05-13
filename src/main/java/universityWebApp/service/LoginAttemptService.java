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
        System.out.println("1");
    }

    public void loginSucceeded(String key) {
        System.out.println("2");
        Blacklist black;
        System.out.println("3");
        try {
            System.out.println("4");
            black = blacklistRepository.findById(key).orElseThrow(IpNotFoundException::new);
            System.out.println("5");
            black.setAttempts(0);
            System.out.println("6");
        }
        catch(IpNotFoundException i){
            System.out.println("7");
            black = new Blacklist(key, 0);
            System.out.println("8");
            blacklistRepository.save(black);
            System.out.println("9");
        }
    }

    public void loginFailed(String key) {
        System.out.println("10");
        int attempts = 0;
        System.out.println("11");
        Blacklist black;
        System.out.println("12");
        try{
            System.out.println("13");
            black =  blacklistRepository.findById(key).orElseThrow(IpNotFoundException::new);
            System.out.println("14");
            attempts =black.getAttempts();
            System.out.println("15");
            black.setAttempts(attempts++);
            System.out.println("16");
        }
        catch(IpNotFoundException i) {
            System.out.println("17");
            black = new Blacklist(key, 1);
            System.out.println("18");
            blacklistRepository.save(black);
            System.out.println("19");
        }

    }

    public boolean isBlocked(String key) {
        try {
            System.out.println("20");
            Blacklist black =  blacklistRepository.findById(key).orElseThrow(IpNotFoundException::new);
            System.out.println("21");
            return black.getAttempts() >= 3;
        } catch (IpNotFoundException e) {
            System.out.println("22");
            return false;
        }
    }


}