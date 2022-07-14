package awaken.awakenauthservice.exception;


import org.springframework.http.HttpStatus;

public class ApiExceptionBuilder {

  public static ApiException InvalidCredentialException(String message){
    return  new ApiException()
            .setCode(HttpStatus.UNAUTHORIZED)
            .setMessage(message);
  };
}
