package universityWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyCsrfSessionAuthenticationStrategy implements SessionAuthenticationStrategy {

    @Autowired
    private CsrfTokenRepository csrfTokenRepository;

    /**
     * Creates a new instance
    // * @param csrfTokenRepository the {@link CsrfTokenRepository} to use
     */
    public MyCsrfSessionAuthenticationStrategy(CsrfTokenRepository csrfTokenRepository) {
        Assert.notNull(csrfTokenRepository, "csrfTokenRepository cannot be null");
        this.csrfTokenRepository = csrfTokenRepository;
    }

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws SessionAuthenticationException {
        boolean containsToken = this.csrfTokenRepository.loadToken(request) != null;

        String url = request.getRequestURL().toString();
        if (containsToken && !url.contains("ico") && !url.contains("img") && !url.contains("css")) {
            this.csrfTokenRepository.saveToken(null, request, response);

            CsrfToken newToken = this.csrfTokenRepository.generateToken(request);
            this.csrfTokenRepository.saveToken(newToken, request, response);

            request.setAttribute(CsrfToken.class.getName(), newToken);
            request.setAttribute(newToken.getParameterName(), newToken);
        }
    }
}
