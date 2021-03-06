package es.ucm.ric.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import es.ucm.ric.MyApp;
import es.ucm.ric.R;

public class SplashActivity extends Activity{

	// used to know if the back button was pressed in the splash screen activity and avoid opening the next activity
    private boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 1500; // 2 seconds
 
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       
 
        setContentView(R.layout.activity_splash);
 
        Handler handler = new Handler();
 
        // run a thread after 2 seconds to start the home screen
        handler.postDelayed(new Runnable() {
 
            @Override
            public void run() {
               
               
                if (!mIsBackButtonPressed) {
                	
                	SharedPreferences prefs = SplashActivity.this.getSharedPreferences(MyApp.PREFERENCES_FILE, Context.MODE_PRIVATE);
                	
                	String email = prefs.getString("email", "");
                	String pass = prefs.getString("pass", "");

                	if(email.equals("")){
                		Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                		SplashActivity.this.startActivity(intent); 
                	}
                	else{
                		Intent intent = new Intent(SplashActivity.this, DrawerActivity.class);
                    	startActivity(intent);
                	}

                }
                
                // 	make sure we close the splash screen so the user won't come back when it presses back key
                finish();
                overridePendingTransition(R.anim.activityfadein,R.anim.splashfadeout);
            }
 
        }, SPLASH_DURATION); // time in milliseconds (1 second = 1000 milliseconds) until the run() method will be called
 
    }
 
    @Override
   public void onBackPressed() {
 
        // set the flag to true so the next activity won't start up
    	// anbd avoid interruput the splash activity
        mIsBackButtonPressed = true;
        super.onBackPressed();
 
    }

}
