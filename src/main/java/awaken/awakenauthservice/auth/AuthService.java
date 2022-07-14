package awaken.awakenauthservice.auth;

import awaken.awakenauthservice.session.Session;
import awaken.awakenauthservice.session.SessionRepository;
import awaken.awakenauthservice.user.User;
import awaken.awakenauthservice.user.UserCredential;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthService {

  private final SessionRepository sessionRepository;

  public AuthService(SessionRepository sessionRepository){
    this.sessionRepository = sessionRepository;
  }

  protected Session createUserSession(UserCredential credentials){
    Session session = new Session()
            .user(credentials.getUser().join());

    return sessionRepository.save(session);
  }

  @Async
  protected CompletableFuture<Optional<Session>> getSession(String sessionId){
    return sessionRepository.getSessionById(sessionId);
  }

  @Async
  protected CompletableFuture<Optional<Session>> deleteSession(String sessionId){
    return sessionRepository.deleteSessionById(sessionId);
  }

  @Async
  protected CompletableFuture<Optional<List<Session>>> deleteSession(User user){
    return sessionRepository.deleteSessionByUser(user);
  }
}
