package awaken.awakenauthservice.session;
import awaken.awakenauthservice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
  @Async
  CompletableFuture<Optional<Session>> getSessionById(String id);

  @Async
  CompletableFuture<Optional<Session>> deleteSessionById(String id);

  @Async
  CompletableFuture<Optional<List<Session>>> deleteSessionByUser(User user);
}
