package ch.raiffeisen.hystrix.demo;

import java.util.Date;

import static ch.raiffeisen.hystrix.demo.Demo.*;

public class RemoteUserClient {
	
	private static User fallback = new User();
	 
	static {
		fallback.lastname = "Muster";
		fallback.firstname = "Hans";
		fallback.userid = "upr1234";
		fallback.telefonnummer = "5678";
		fallback.readtime = new Date();
		fallback.state = User.State.fallbacked;
	}
	
	public RemoteUserClient() {
		super();
	}

	/**
	 * Service-Client zum Laden eines Users  anhand der Userid
   * aus einem Remote-Service.
   *
	 * @param userid die Userid
	 * @return der User
	 * @throws Exception Fehler beim Laden des Users
	 */
	public static User loadUser(String userid) throws Exception {

		/* Normalerweise würde der Client hier die Daten von einem anderen System via
		   Service-Aufruf laden... zur Vereinfachung werden die Daten hier einfach
		   im Client bereitgehalten */
		if (!serviceConfig.serviceProperties.online) {
			throw new RuntimeException("Service down");
		}

		if (userid.equals("upr1234")) {
			User person = new User(fallback);
			person.readtime = new Date();
			person.state = User.State.live;

			// Wir simulieren die Antwortzeit des Services und die Netzwerklatenz
			Thread.sleep(serviceConfig.serviceProperties.worktime);

			return person;
		}
		
		throw new RuntimeException("User not found");
	}

	public static User loadFallback (String userid) {
		if (fallback.userid.equals(userid)) {
			return fallback;
		}
		throw new RuntimeException("Service down");
	}

}