package awaken.awakenauthservice.user;

import lombok.Data;
import java.util.concurrent.CompletableFuture;

@Data
public class UserCredentialGoogle implements  UserCredential {

  public String token;
  @Override
  public CompletableFuture<User> getUser() {
    return null;
  }
}
