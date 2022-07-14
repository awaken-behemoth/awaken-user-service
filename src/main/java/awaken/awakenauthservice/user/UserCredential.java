package awaken.awakenauthservice.user;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserCredentialBasic.class, name = "basic"),
        @JsonSubTypes.Type(value = UserCredentialGoogle.class, name = "google")
})
public interface UserCredential {
  @Async
  CompletableFuture<User> getUser();
}
