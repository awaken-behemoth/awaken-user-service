package awaken.awakenauthservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AuthExceptionController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<Object> handleApiException(ApiException ex) {
    return ResponseEntity.status(HttpStatus.OK).body(ex.DTO());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleRuntimeException() {
    return ResponseEntity
            .status(500)
            .body(new ApiException()
                          .setCode(500)
                          .setMessage("Something went wrong").DTO()
            );
  }
}
