package universityWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import universityWebApp.filter.CustomUsernamePasswordAuthenticationToken;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private LoginAttemptService loginAttemptService;

    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        String ipAddress = ((CustomUsernamePasswordAuthenticationToken) e.getAuthentication()).getIpAddress();

        loginAttemptService.loginFailed(ipAddress);
    }
}
