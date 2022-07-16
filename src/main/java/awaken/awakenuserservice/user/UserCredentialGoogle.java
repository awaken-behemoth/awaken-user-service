package awaken.awakenuserservice.user;

import lombok.Data;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Data
public class UserCredentialGoogle implements  UserCredential {
  private String token;

  public String getUsername(){
    return "";
  }
  public String getEmail(){
    return "";
  };

  public String getGoogleId(){
   return "";
  }
  @Override
  public CompletableFuture<Optional<User>> getUser(UserRepository userRepository) {
    return null;
  }
}
