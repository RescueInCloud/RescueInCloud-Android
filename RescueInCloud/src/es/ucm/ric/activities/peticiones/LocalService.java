package es.ucm.ric.activities.peticiones;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import es.ucm.ric.activities.listeners.UpdateListener;



public class LocalService extends Service{
    // Like in the Service sample code, plus:

    public static String ACTION_START = "es.ucm.ric.servicio";

    private UpdateListener listener;

    public void setListener(UpdateListener listener) {
    	this.listener = listener; 
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        if (ACTION_START.equals(intent.getAction())) {
          
        	//conexion
        	
        }
        return START_STICKY;
    }

    public void onDestroy() {
    }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}