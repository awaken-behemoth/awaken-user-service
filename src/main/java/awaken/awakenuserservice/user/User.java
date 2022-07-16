package awaken.awakenuserservice.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.persistence.*;

@Entity @Getter
@Accessors(chain = true)
@Table(name = "awaken.users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Setter  private long id;

  @Column(name = "email", unique = true)
  @Setter private String email;

  @Column(name = "password")
  @Setter  private String password;

  @Column(name = "phone_number")
  @Setter private String phoneNumber;

  @Column(name = "username", unique = true)
  @Setter private String username;

  @Column(name = "credential_type")
  @Setter private UserCredentialType credentialType;

  @Column( name = "oauth_id")
  @Setter private String oauthId;

  private boolean enabled = true;
  private boolean accountNonLocked = true;
  private boolean accountNonExpired = true;
  private boolean credentialsNonExpired = true;
}
