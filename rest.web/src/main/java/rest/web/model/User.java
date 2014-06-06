package rest.web.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class User {
   private Map<String, Info> model = Collections.synchronizedMap( new HashMap<String, Info>() );

   private class Info {

      private String name;

      private String invention;

      Info( String name, String invention ) {
         this.name = name;
         this.invention = invention;
      }

      String toJson() {
         return "{ \"name\" : \"" + this.name + "\", \"invention\" : \"" + this.invention + "\" }";
      }
   }

   public void add( String userid, String name, String invention ) {
      this.model.put( userid, new Info( name, invention ) );
   }

   public Map<String, Info> getModel() {
      return model;
   }

   public void setModel( Map<String, Info> model ) {
      this.model = model;
   }

   public String toJson( String userId ) {
      Info info = getModel().get( userId );
      if( info != null ) {
         return info.toJson();
      }
      else {
         return null;
      }
   }

   public String toJson() {
      StringBuffer json = new StringBuffer();
      boolean first = true;
      json.append( "[" );
      for( String name : this.model.keySet() ) {
         if( first ) {
            first = false;
         }
         else {
            json.append( ", " );
         }
         json.append( "/rest/users/" + name );
      }
      json.append( "]" );
      return json.toString();
   }
}
