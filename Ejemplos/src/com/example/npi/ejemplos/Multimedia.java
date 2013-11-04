package com.example.npi.ejemplos;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class Multimedia extends Activity {
	MediaPlayer mediaPlayer ;
	
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState ) ;
		TextView textView = new TextView( this ) ;
		setContentView( textView  ) ;
		setVolumeControlStream( AudioManager.STREAM_MUSIC ) ;
		mediaPlayer = new MediaPlayer() ;
		
		try {
			AssetManager assetManager = getAssets() ;
			AssetFileDescriptor descriptor = assetManager.openFd( "Sound/Sonido1.mp3" ) ;
			mediaPlayer.setDataSource( descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength() ) ;
			mediaPlayer.start() ;
			mediaPlayer.setLooping( true ) ;
		}
		
		catch (IOException e ) {
			textView.setText( "No se ha podido cargar el archivo de Musica " + e.getMessage() ) ;
			mediaPlayer = null ;
		}
	}
	
	protected void onResume() {
		super.onResume() ;
		
		if( mediaPlayer != null ) {
			mediaPlayer.start() ;
		}
	}
	
	protected void onPause() {
		super.onPause() ;
		
		if ( mediaPlayer != null ) {
			mediaPlayer.pause() ;
			
			if( isFinishing() ) {
				mediaPlayer.stop() ;
				mediaPlayer.release() ;
			}
		}
	}
}