package ch.raiffeisen.hystrix.demo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;

@JsonSerialize(typing=Typing.STATIC)
public class User {

	public String firstname;
	public String lastname;
	public String userid;
	public String telefonnummer;
	public Date readtime;
	public State state;

	public enum State {
		live,
		fallbacked
	}

	public User(){
    super();
  }

	public User(User user) {
		this.firstname = user.firstname;
		this.lastname = user.lastname;
		this.userid = user.userid;
		this.telefonnummer = user.telefonnummer;
	}

}

