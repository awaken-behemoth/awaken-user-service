package awaken.awakenauthservice.auth;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @GetMapping("token")
  public String getTreeById() {
    return "Hello world 20";
  }
}
