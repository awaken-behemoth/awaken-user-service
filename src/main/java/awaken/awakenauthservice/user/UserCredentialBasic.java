package awaken.awakenauthservice.user;

import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
public class UserCredentialBasic implements UserCredential {

  private String username;
  private String password;

  public UserCredentialBasic(String username, String password){
   this.username = username;
   this.password = password;
  }

  @Override
  public CompletableFuture<User> getUser() {
    return null;
  }
}
