package ee.uptime.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.SERVICE_UNAVAILABLE)
public class UnavailableServiceException extends RuntimeException {
}
