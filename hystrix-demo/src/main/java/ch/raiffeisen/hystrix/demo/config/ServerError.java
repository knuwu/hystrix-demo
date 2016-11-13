package ch.raiffeisen.hystrix.demo.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;

/**
 * Output-DTO für Server-Fehler, d.h. http-Statuscodes des Bereichs 5xx.
 *
 * @author upr5579 {@code <roberto.depellegrini@raiffeisen.ch>}
 */
@JsonSerialize(typing=Typing.STATIC)
public class ServerError {

	/** http-Statuscode (gleich wie im http-Header) */
	@JsonProperty(required=true)
	public Integer httpStatusCode;

	/**
	 * Fehlercode, z.B. "IO_EXCEPTION"
	 */
	@JsonProperty(required=true)
	public String code;

	/**
	 * Kurze Beschreibung des Fehlers (für Logging, nicht für den Benutzer bestimmt).
	 */
	@JsonProperty(required=true)
	public String logMessage;

}
