package faces.web;

import org.springframework.stereotype.Component;

@Component
public class UserBean {

   private String name;

   public String getName() {
      return "user bean:" + name;
   }

   public void setName( String name ) {
      this.name = name;
   }
}
