package com.example.npi.ejemplos;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;


public class PantallaCompleta extends UniTactil {
	WakeLock wakeLock ;
	
    @Override
    public void onCreate( Bundle savedInstanceState ) {
		PowerManager powerManager = (PowerManager)getSystemService( Context.POWER_SERVICE ) ; //Evitamos que se "suspenda" el dispositivo
    	wakeLock = powerManager.newWakeLock( PowerManager.FULL_WAKE_LOCK, "My Lock" ) ;
        requestWindowFeature( Window.FEATURE_NO_TITLE ) ; //Pantalla Completa
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN ) ; //Pantalla Completa
    	super.onCreate( savedInstanceState ) ;
    }
    
    protected void onResume() {
    	super.onResume() ;
    	wakeLock.acquire() ;
    }
    
    protected void onPause() {
    	super.onPause() ;
    	wakeLock.release() ;
    }
}
