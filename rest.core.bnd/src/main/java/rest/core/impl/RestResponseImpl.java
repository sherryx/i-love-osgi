package rest.core.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import rest.core.api.RestResponse;

@Component("restResponse")
public class RestResponseImpl implements RestResponse {

   @Override
   public ResponseEntity<String> createResponseEntity( String json, HttpStatus status ) {
      HttpHeaders headers = new HttpHeaders();
      headers.add( "Content-Type", "application/json; charset=utf-8" );
      return new ResponseEntity<String>("update from rest bnd1:"+ json + "\n", headers, status );
   }

}
