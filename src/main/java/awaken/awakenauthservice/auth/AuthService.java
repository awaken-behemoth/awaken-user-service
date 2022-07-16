package awaken.awakenauthservice.auth;

import awaken.awakenauthservice.session.Session;
import awaken.awakenauthservice.session.SessionRepository;
import awaken.awakenauthservice.user.User;
import awaken.awakenauthservice.user.UserCredential;
import awaken.awakenauthservice.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static awaken.awakenauthservice.exception.ApiExceptionBuilder.InvalidCredentialException;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final SessionRepository sessionRepository;

  @Autowired
  public AuthService(SessionRepository sessionRepository, UserRepository userRepository) {
    this.userRepository = userRepository;
    this.sessionRepository = sessionRepository;
  }

  private static Session unwrapSession(Optional<Session> session) {
    return session.orElseThrow(() -> InvalidCredentialException("Invalid or expired session id"));
  }

  protected Session createUserSession(UserCredential credentials) {
    Session session = new Session()
            .setUser(credentials.getUser(userRepository)
                          .join()
                          .orElseThrow(() -> InvalidCredentialException("Wrong username or password")));
    return sessionRepository.save(session);
  }

  @Async
  protected CompletableFuture<Session> getSession(String sessionId) {
    return sessionRepository
            .getSessionById(sessionId)
            .thenApply(AuthService::unwrapSession);
  }

  @Async
  protected CompletableFuture<Session> deleteSession(String sessionId) {
    return sessionRepository
            .deleteSessionById(sessionId)
            .thenApply(AuthService::unwrapSession);
  }

  @Async
  protected CompletableFuture<List<Session>> deleteSession(User user) {
    return sessionRepository.deleteSessionByUser(user);
  }

}
