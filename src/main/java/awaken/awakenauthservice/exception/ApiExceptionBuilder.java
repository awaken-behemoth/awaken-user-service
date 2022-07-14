package awaken.awakenauthservice.exception;


public class ApiExceptionBuilder {

  public static ApiException InvalidCredentialException(String message){
    return  new ApiException()
            .setCode(401)
            .setMessage(message);
  };
}
