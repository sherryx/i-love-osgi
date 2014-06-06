package faces.web;

import java.io.Serializable;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class HelloBean implements Serializable, InitializingBean {

   private static final long serialVersionUID = 1L;
   // private String name;

   @Autowired
   private UserBean user;

   public String getName() {
      //return "still hate osgi:" + name;
      return user.getName();
   }

   public void setName( String name ) {
      // this.name = name;
      user.setName( name );
   }

   @Override
   public void afterPropertiesSet() throws Exception {
      System.out.println( "hello===" );
   }

}
