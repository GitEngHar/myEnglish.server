package myenglish.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExceptionController {
    @ExceptionHandler(InvalidRequestException.class)
    public ProblemDetail InvalidRequestExceptionHandler(InvalidRequestException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setDetail("InvalidRequest");
        return problemDetail;
    }
}
