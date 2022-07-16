package awaken.awakenauthservice.user;

import awaken.awakenauthservice.utils.PasswordHasher;
import lombok.Data;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Data
public class UserCredentialBasic implements UserCredential {

  private String username;
  private String password;

  public UserCredentialBasic(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public CompletableFuture<Optional<User>> getUser(UserRepository userRepository) {
    return userRepository.findByUsername(username)
            .thenApply((user) -> {
              if (user.isEmpty() || !PasswordHasher.match(this.password, user.get().getPassword()))
                return Optional.empty();

              return Optional.of(new User());
            });
  }

}
