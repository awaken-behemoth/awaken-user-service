package awaken.awakenuserservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<Object> handleApiException(ApiException ex) {
    return ResponseEntity.status(ex.getCode()).body(ex.DTO());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleRuntimeException() {
    return ResponseEntity
            .status(500)
            .body(new ApiException()
                          .setCode(HttpStatus.INTERNAL_SERVER_ERROR)
                          .setMessage("Something went wrong").DTO()
            );
  }

}
