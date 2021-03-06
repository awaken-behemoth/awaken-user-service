package awaken.awakenuserservice.exception;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApiExceptionDTO {
  private String code;
  private String type;
  private String message;
  private String timeStamp;

  ApiExceptionDTO(ApiException exception) {
    this.message = exception.getMessage();
    this.type = exception.getCode().name();
    this.timeStamp = exception.getTimeStamp();
    this.code = exception.getCode().toString();
  }
}
