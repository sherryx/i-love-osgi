package rest.core.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface RestResponse {

   ResponseEntity<String> createResponseEntity( String json, HttpStatus status );
}
