package awaken.awakenauthservice.auth;

import awaken.awakenauthservice.user.UserCredential;
import awaken.awakenauthservice.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController @RequestMapping("/auth")
public class AuthController {
  @Autowired private AuthService authService;
  @Autowired private UserRepository userRepository;

  @PostMapping("token")
  public void getUserAuthenticationToken(@RequestBody UserCredential userCredential,
                                         HttpServletResponse response) {

    String sessionId = authService.createUserSession(userCredential).id();
    response.addCookie(new Cookie("session-id", sessionId));
    return;
  }

}
