package ch.raiffeisen.hystrix.demo;

import java.text.SimpleDateFormat;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ch.raiffeisen.hystrix.demo.Demo.serviceConfig;

@Path("/users")
public class UserResource extends Application {

  private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);
	private static final SimpleDateFormat formatter = new SimpleDateFormat("d.M.Y H:m:s.S");

	@GET
	@Path("/{userid}")
	@Produces("application/json")
	public User searchUser(@PathParam("userid") String userid) throws Exception {
		User user;
		try {
			if (serviceConfig.serviceProperties.useHystrix) {
				user = new LookupCommand(userid).execute();
			} else {
				user = RemoteUserClient.loadUser(userid);
			}
			LOG.info("User: {} {} ({})", user.userid, formatter.format(user.readtime), user.state);

			// Zweiter Aufruf mit Cache
			//user = new LookupCommand(userid).execute();
      //LOG.info("Cached User: {} {} ({})", user.userid, formatter.format(user.readtime), user.state);

			return user;
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			throw e;
		}
	}

}