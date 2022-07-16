package awaken.awakenuserservice.user;

import awaken.awakenuserservice.utils.PasswordHasher;
import awaken.awakenuserservice.utils.SqlQueryWrapper;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static awaken.awakenuserservice.exception.ApiExceptionBuilder.ConflictingRessourceException;

@Service
public class UserService {

  final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(UserCredential credential) {
    User user;

    if ( credential instanceof UserCredentialBasic){
      user = createBasicUser((UserCredentialBasic) credential);
    }
    else if ( credential instanceof UserCredentialGoogle){
      user = createGoogleUser((UserCredentialGoogle) credential);
    }
    else throw new RuntimeException("Invalid user credential encountered");

    try {
      SqlQueryWrapper.excecute(() -> userRepository.save(user));
    } catch (JDBCException e) {
      throw ConflictingRessourceException("Email already in use");
    }

    return user;
  }

  private User createBasicUser(UserCredentialBasic credentials){

    return new User()
            .setEmail(credentials.getUsername())
            .setUsername(credentials.getUsername())
            .setCredentialType(UserCredentialType.BASIC)
            .setPassword(PasswordHasher.hashString(credentials.getPassword()));
  }

  private User createGoogleUser(UserCredentialGoogle credential ){

    return new User()
            .setEmail(credential.getEmail())
            .setOauthId(credential.getGoogleId());
  }

}
