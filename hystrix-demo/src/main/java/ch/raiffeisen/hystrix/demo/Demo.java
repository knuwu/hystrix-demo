package ch.raiffeisen.hystrix.demo;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Root Resource der Demo
 * Created by ralph on 13.11.16.
 */
@ApplicationPath("/")
public class Demo extends Application {

  static public Configuration serviceConfig = new Configuration();
}
