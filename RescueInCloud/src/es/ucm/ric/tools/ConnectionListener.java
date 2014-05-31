package es.ucm.ric.tools;

import android.content.Context;
/**
 * 
 * @author Ricardo Champa
 *
 */
public interface ConnectionListener {
	
	public static String code="bd5e6b731c8bdf0b911bea5d5279f058";

	boolean validateDataBeforeConnection(String... params);
	void invalidInputData();
	boolean inBackground(String... params);
	void afterGoodConnection();
	void afterErrorConnection();
	Context getContext();
}
