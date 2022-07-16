package awaken.awakenauthservice.auth;

import awaken.awakenauthservice.session.Session;
import awaken.awakenauthservice.user.User;
import awaken.awakenauthservice.user.UserCredential;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("token")
  public void getUserAuthenticationToken(@RequestBody UserCredential userCredential,
                                         HttpServletResponse response) {
    String sessionId = authService.createUserSession(userCredential).getId();
    Cookie cookie = new Cookie("session-id", sessionId);
    cookie.setPath("/");
    cookie.setSecure(true);
    cookie.setHttpOnly(true);
    response.addCookie(cookie);
  }

  @DeleteMapping("token/{id}")
  public void deleteUserAuthenticationToken(@PathVariable("id") String id) {
    authService.deleteSession(id);
  }

  @GetMapping("user/{session-id}")
  public User getUserBySessionId(@PathVariable("session-id") String id) {
    Session session = authService.getSession(id).join();
    return session.getUser();
  }

}
