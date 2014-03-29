package es.ucm.ric.tools;

import android.content.Context;
/**
 * 
 * @author Ricardo Champa
 *
 */
public interface ConnectionListener {

	boolean validateDataBeforeConnection(String... params);
	void invalidInputData();
	boolean inBackground(String... params);
	void afterGoodConnection();
	void afterErrorConnection();
	Context getContext();
}
