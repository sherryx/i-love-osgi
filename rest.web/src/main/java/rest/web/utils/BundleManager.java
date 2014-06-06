package rest.web.utils;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import rest.web.model.User;

@Configurable
public class BundleManager {

   private String bundleId;

   public BundleManager( String bundleId ) {
      super();
      this.bundleId = bundleId;
   }

   @Autowired
   BundleContext bundleContext;

   @Autowired
   User user;

   public Bundle getBundle( String symbolicName ) {
      Bundle result = null;
      for( Bundle candidate : bundleContext.getBundles() ) {
         if( candidate.getSymbolicName().equals( symbolicName ) ) {
          //  if( result == null || result.getVersion().compareTo( candidate.getVersion() ) < 0 ) {
               result = candidate;
           // }
         }
      }
      return result;
   }

   public void updateBundle() {
      Bundle bundle = getBundle( bundleId );
      if( bundle != null ) {
         System.out.println( "===found bundle" );
         try {
            bundle.update();
           // bundle.start();
         }
         catch( BundleException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      }
   }
}
