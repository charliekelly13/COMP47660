package universityWebApp;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Utilities {
    public static String getIP(HttpServletRequest request) {
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
