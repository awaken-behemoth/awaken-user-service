package awaken.awakenauthservice.user;

import lombok.Data;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Data
public class UserCredentialGoogle implements  UserCredential {
  public String token;
  @Override
  public CompletableFuture<Optional<User>> getUser(UserRepository userRepository) {
    return null;
  }
}
