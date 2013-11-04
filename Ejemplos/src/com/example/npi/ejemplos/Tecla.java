package com.example.npi.ejemplos;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.TextView;

public class Tecla extends Activity implements OnKeyListener {
	StringBuilder builder = new StringBuilder() ;
	TextView textView ;
	
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState ) ;
		textView = new TextView( this ) ; 
		textView.setText( "Presiona una tecla (en caso de disponer de alguna)" ) ;
		textView.setOnKeyListener( this ) ; //Establecemos que el textView "escuche" cualquier pulsacion de tecla
		textView.setFocusableInTouchMode( true ) ;
		textView.requestFocus() ;
		setContentView( textView ) ;
	}
	
	@Override 
	public boolean onKey( View view, int keyCode, KeyEvent event ) {
		builder.setLength( 0 ) ;
		
		switch( event.getAction() ) { //Segun el tipo de accion vamos añadiendo los "mensajes" al builder 
		
		case KeyEvent.ACTION_DOWN:
			builder.append( "pulsada, " ) ;
			break ;
		case KeyEvent.ACTION_UP:
			builder.append( "liberada, " ) ;
			break ;
		}
		
		builder.append( event.getKeyCode() ) ;
		builder.append( ", " ) ;
		builder.append( (char)event.getUnicodeChar() ) ; //Codigo de la tecla
		String text = builder.toString() ;
		Log.d( "Tecla", text ) ;
		textView.setText( text ) ;
		
		if ( event.getKeyCode() == KeyEvent.KEYCODE_BACK ) {
			return false ;
		}
		else 
			return true ;
		
		}
	}
