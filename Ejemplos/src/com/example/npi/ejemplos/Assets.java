package com.example.npi.ejemplos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

public class Assets extends Activity {
	
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState ) ;
		TextView textView = new TextView( this ) ;
		setContentView( textView ) ; //Establecemos que el contenido de la ventana sera nuestro textView
		AssetManager assetManager = getAssets() ; //Obtenemos los assets
		InputStream inputStream = null ; 
		
		try {
			inputStream = assetManager.open( "text/test.txt" ) ; //Usamos como flujo de entrada el fichero
			String text = loadTextFile( inputStream ) ;
			textView.setText( text ) ; //Lo mostramos
		}
		catch (IOException e ) {
			textView.setText( "No se ha podido cargar el fichero" ) ;
		}
		finally {
			
			if ( inputStream != null ) {
				
				try {
					inputStream.close() ;
				}
				catch (IOException e ) {
					textView.setText( "No se ha podido cerrar el fichero" ) ;
				}
			}
		}
	}
	
	public String loadTextFile( InputStream inputStream ) throws IOException { //Funcion para decodificar el archivo de texto
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream() ;
		byte[] bytes = new byte[4096] ;
		int len = 0 ;
		
		while( ( len = inputStream.read( bytes ) ) > 0 ) {
			byteStream.write( bytes, 0, len ) ;
		}
		
		return new String( byteStream.toByteArray(), "UTF8" ) ;
		}
	}
