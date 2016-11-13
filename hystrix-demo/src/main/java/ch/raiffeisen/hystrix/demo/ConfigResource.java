package ch.raiffeisen.hystrix.demo;

import ch.raiffeisen.hystrix.demo.config.HystrixProperty;
import com.netflix.config.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;

/**
 * Created by ralph on 13.11.16.
 */
@Path("/config")
public class ConfigResource {

  private static final Logger LOG = LoggerFactory.getLogger(ConfigResource.class);

  @POST
  @Path("/")
  @Consumes("application/json")
  public void setServiceConfig(Configuration config) {
    Demo.serviceConfig.serviceProperties.online = config.serviceProperties.online;
    LOG.info("Set Service Property 'online' to value: " + Demo.serviceConfig.serviceProperties.online);
    Demo.serviceConfig.serviceProperties.worktime = config.serviceProperties.worktime;
    LOG.info("Set Service Property 'worktime' to value: " + Demo.serviceConfig.serviceProperties.worktime);
    Demo.serviceConfig.serviceProperties.useHystrix = config.serviceProperties.useHystrix;
    LOG.info("Set Service Property 'useHystrix' to value: " + Demo.serviceConfig.serviceProperties.useHystrix);

    for (HystrixProperty property : config.globalProperies) {
      if (property != null) {
        ConfigurationManager.getConfigInstance().setProperty(property.key, property.value);
        LOG.info("Set Property: " + property.key + " to value: " + property.value);
        Demo.serviceConfig.globalProperies = config.globalProperies;
      }
    }

    for (HystrixProperty property : config.instanceProperies) {
      if (property != null) {
        ConfigurationManager.getConfigInstance().setProperty(property.key, property.value);
        LOG.info("Set Property: " + property.key + " to value: " + property.value);
        Demo.serviceConfig.instanceProperies = config.instanceProperies;
      }
    }
  }

  @GET
  @Path("/")
  @Produces("application/json")
  public Configuration getServiceConfig() {
    return Demo.serviceConfig;
  }

}
