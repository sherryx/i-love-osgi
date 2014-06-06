package rest.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import rest.core.api.RestResponse;
import rest.web.model.User;
import rest.web.utils.BundleManager;

/**
 * <p>
 * {@link RestController} uses Spring MVC to implement a REST service which associates names and inventions with
 * "user ids".
 * <p/>
 * GET requests may specify a user id, e.g.:
 * 
 * <pre>
 * GET /rest/users/roy HTTP/1.1
 * Accept: application/json
 * </pre>
 * 
 * or may be used to inquire all users, e.g.:
 * 
 * <pre>
 * GET /rest/users HTTP/1.1
 * Accept: application/json
 * </pre>
 * 
 * whereas PUT requests specify a user id, a name, and an invention, e.g.:
 * 
 * <pre>
 * PUT /rest/users/james/Sir%20James%20Dyson/Cyclonic%20Separation HTTP/1.1
 * </pre>
 * 
 * </p>
 * You can use curl to drive this program as follows:
 * 
 * <pre>
 * curl -i -H "Accept: application/json" http://localhost:8080/rest/users/roy
 * curl -i -X PUT http://localhost:8080/rest/users/james/Sir%20James%20Dyson/Cyclonic%20Separation
 * curl -i -H "Accept: application/json" http://localhost:8080/rest/users/james
 * curl -i -H "Accept: application/json" http://localhost:8080/rest/users
 * </pre>
 * 
 * The implementation is deliberately primitive.
 * <p/>
 * Please consult the following for more information on REST and its support in Spring:
 * <p/>
 * <ul>
 * <li><a href="http://en.wikipedia.org/wiki/Representational_state_transfer">Representational State Transfer</a>
 * (Wikipedia article)
 * <li><a href="http://www.ics.uci.edu/~fielding/pubs/dissertation/top.htm">Architectural Styles and the Design of
 * Network-based Software Architectures</a> (Roy Fielding's REST dissertation)</li>
 * <li><a href="http://static.springsource.org/spring/docs/3.1.0.RELEASE/reference/html/mvc.html">Spring Web MVC
 * framework<a/></li>
 * <li><a href="http://static.springsource.org/spring-roo/reference/html/base-json.html">Spring Roo JSON Add-On</a></li>
 * </ul>
 * <p/>
 * 
 * <strong>Concurrent Semantics</strong><br />
 * 
 * Thread safe.
 * 
 */
@Controller
public final class RestController implements InitializingBean {

   @Autowired
   RestResponse restResponse;

   @Autowired
   User user;

   public RestController() {

   }

   @Override
   public void afterPropertiesSet() throws Exception {
      user.add( "roy3", "Roy T. Fielding", "Representational State Transfer" );
   }

   @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public ResponseEntity<String> getUsers() {
      BundleManager manager = new BundleManager( "yaas.deployment-1-faces.web" );
      manager.updateBundle();
      return restResponse.createResponseEntity( user.toJson(), HttpStatus.OK );
   }

   @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public ResponseEntity<String> getUser( @PathVariable("userId") String userId ) {
      String json = user.toJson( userId );
      if( json != null ) {
         return restResponse.createResponseEntity( json, HttpStatus.OK );
      }
      else {
         return restResponse.createResponseEntity( "", HttpStatus.NOT_FOUND );
      }
   }

   @RequestMapping(value = "/users/{userId}/{name}/{invention}", method = RequestMethod.PUT)
   public void putUser( @PathVariable("userId") String userId, @PathVariable("name") String name, @PathVariable("invention") String invention,
         HttpServletResponse httpServletResponse ) {
      user.add( userId, name, invention );
      httpServletResponse.setStatus( HttpServletResponse.SC_OK );
   }

}
