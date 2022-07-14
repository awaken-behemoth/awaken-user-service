package awaken.awakenauthservice.session;

import awaken.awakenauthservice.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Entity
@Accessors(chain = true, fluent = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "awaken.sessions")
public class Session {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter private String id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @Setter @Getter private User user;

}
