package ch.raiffeisen.hystrix.demo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LookupCommand extends HystrixCommand<User>{

	private static final Logger LOG = LoggerFactory.getLogger(LookupCommand.class);

	private String userid;

	public LookupCommand(String userid) {


		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("User")));
//				.andCommandPropertiesDefaults(HystrixPropertiesCommandDefault.Setter()
//						.withExecutionTimeoutInMilliseconds(2000)
//						.withCircuitBreakerErrorThresholdPercentage(20)));
		
		this.userid = userid;
	}

	@Override
	protected User run() throws Exception {
		return RemoteUserClient.loadUser(userid);
	}
	
	@Override
	protected String getCacheKey() {
		return userid;
	}

	@Override
	protected User getFallback() {
		return RemoteUserClient.loadFallback(userid);
	}

}
