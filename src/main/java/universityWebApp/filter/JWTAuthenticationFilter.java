
package universityWebApp.filter;


import com.auth0.jwt.JWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import universityWebApp.controller.FeesController;
import universityWebApp.model.Student;
import universityWebApp.model.User;
import universityWebApp.repository.StaffRepository;
import universityWebApp.repository.StudentRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static universityWebApp.filter.SecurityConstants.*;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StaffRepository staffRepository;

    Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter .class);
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
        this.authenticationManager = authenticationManager;
        this.studentRepository = ctx.getBean(StudentRepository.class);
        this.staffRepository = ctx.getBean(StaffRepository.class);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getParameter("username"),
                request.getParameter("password"),
                new ArrayList<>());

        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        String role;
        String id;

        String username = ((UserDetails) auth.getPrincipal()).getUsername();

        if (auth.getAuthorities().iterator().next().getAuthority().equals("student")) {
            role = "student";
            id = studentRepository.findStudentByUsername(username).getId();
        } else {
            role = "staff";
            id = staffRepository.findStaffByUsername(username).getId();
        }
        logger.info(String.format("Successful login to %s %s by IP %s", role, username, getIP(request)));
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("role", role)
                .withClaim("id", id)
                .sign(HMAC512(SECRET.getBytes()));

        addCookie(token, response);

        new DefaultRedirectStrategy().sendRedirect(request, response, "/");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        String errorType = "";

        if (failed.getMessage().contains("blocked")) {
            errorType = "blocked";
        } else {
            errorType = "invalid";
        }
        String[] username = request.getParameterMap().get("username");
        logger.warn(String.format("Unsuccesful login to username %s by IP %s with error %s", username[0], getIP(request),errorType));
        new DefaultRedirectStrategy().sendRedirect(request, response, "/login?error=" + errorType);
    }

    private void addCookie(String token, HttpServletResponse response) {
        // create a cookie
        Cookie cookie = new Cookie("JWT", token);

        // expires in 1 day
        cookie.setMaxAge(1 * 24 * 60 * 60);

        // optional properties
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        // add cookie to response
        response.addCookie(cookie);
    }


    public String getIP(HttpServletRequest request) {
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
