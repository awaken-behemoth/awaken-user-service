package awaken.awakenuserservice.exception;


import org.springframework.http.HttpStatus;

public class ApiExceptionBuilder {

  public static ApiException InvalidCredentialException(String message) {
    return new ApiException()
            .setCode(HttpStatus.UNAUTHORIZED)
            .setMessage(message);
  }

  public static ApiException ConflictingRessourceException(String message) {
    return new ApiException()
            .setCode(HttpStatus.CONFLICT)
            .setMessage(message);
  }

}
