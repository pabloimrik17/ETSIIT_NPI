package com.example.npi.ejemplos;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CicloVida extends Activity {

	StringBuilder builder = new StringBuilder() ;
	TextView textView ;
	
	private void log( String text ) { //Metodo para imprimir los mensajes segun el estado de la Activity
		Log.d( "CicloVida", text ) ;
		builder.append( text ) ;
		builder.append( '\n' ) ;
		textView.setText( builder.toString() ) ;
	}
	
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState ) ;
		textView = new TextView( this ) ;
		textView.setText( builder.toString() ) ;
		setContentView( textView ) ;
		log( "creada" ) ; //Primera ejecucion
	}
	
	protected void onResume() { //Se desbloquea el dispositivo (o se vuelve a la aplicacion)
		super.onResume() ;
		log( "reanudada" ) ; 
	}
	
	protected void onPause() { //Se bloquea el dispositivo (o se cambia de aplicacion)
		super.onPause() ;
		log( "pausada" ) ;
		
		if( isFinishing() ) { //Se cierra la activity
			log( "finalizando" ) ;
		}
	}
}