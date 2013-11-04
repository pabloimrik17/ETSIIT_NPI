package com.example.npi.ejemplos;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class Sonidos extends Activity implements OnTouchListener {
	
	SoundPool soundPool ;
	int explosionId = -1 ;
	
	public void onCreate ( Bundle SavedInstanceState ) {
		super.onCreate( SavedInstanceState ) ; 
		TextView textView = new TextView( this ) ;
		textView.setOnTouchListener( this ) ;
		setContentView( textView ) ;
		setVolumeControlStream( AudioManager.STREAM_MUSIC ) ;
		soundPool = new SoundPool( 20, AudioManager.STREAM_MUSIC, 0 ) ;
		
		try {
			AssetManager assetManager = getAssets() ;
			AssetFileDescriptor descriptor = assetManager.openFd( "Sound/sonido.mp3" ) ;
			explosionId = soundPool.load( descriptor, 1 ) ;
		}
		
		catch ( IOException e ) {
			textView.setText( "No se ha podido cargar el fichero de sonido, " + e.getMessage() ) ;
		}
	}
	
	public boolean onTouch( View v, MotionEvent event ) {
		
		if( event.getAction()  == MotionEvent.ACTION_UP ) {
			
			if( explosionId != -1 ) {
				soundPool.play(explosionId, 1, 1, 0, 0, 1 ) ;
			}
		}
		
		return true ;
	}
}