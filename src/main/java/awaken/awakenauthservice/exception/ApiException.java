package awaken.awakenauthservice.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;


@Accessors(chain = true)
public class ApiException extends RuntimeException {
  @Getter @Setter
  private int code;
  @Getter @Setter
  private String message;
  @Getter @Setter
  private String timeStamp = new Date().toString();

  public ApiExceptionDTO DTO(){
    return new ApiExceptionDTO(this);
  }
}
