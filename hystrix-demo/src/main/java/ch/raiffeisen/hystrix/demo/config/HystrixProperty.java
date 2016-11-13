package ch.raiffeisen.hystrix.demo.config;

public class HystrixProperty {
	public String key;
	public String value;

	public HystrixProperty( String key, String value) {
		this.key = key;
		this.value = value;
	}
}