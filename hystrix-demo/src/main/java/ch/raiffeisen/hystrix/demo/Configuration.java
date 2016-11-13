package ch.raiffeisen.hystrix.demo;

import java.util.ArrayList;
import java.util.List;

import ch.raiffeisen.hystrix.demo.config.CustomHystrixProperty;
import ch.raiffeisen.hystrix.demo.config.HystrixProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;

@JsonSerialize(typing=Typing.STATIC)
public class Configuration {
	
	public ServiceProperties serviceProperties = new ServiceProperties();
	
	@JsonDeserialize(using = CustomHystrixProperty.class)
	public List<HystrixProperty> globalProperies = new ArrayList<>();

	@JsonDeserialize(using = CustomHystrixProperty.class)
	public List<HystrixProperty> instanceProperies = new ArrayList<>();

	public class ServiceProperties {
		public boolean online = true;
		public int worktime = 10;
		public boolean useHystrix = true;
	}

}
