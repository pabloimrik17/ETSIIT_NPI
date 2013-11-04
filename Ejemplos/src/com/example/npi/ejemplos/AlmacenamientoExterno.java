package com.example.npi.ejemplos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class AlmacenamientoExterno extends Activity {
	
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState ) ;
		TextView textView = new TextView( this ) ;
		setContentView( textView ) ;
		String state = Environment.getExternalStorageState() ; //Comprobamos si hay una tarjeta, y si esta montada
		
		if( !state.equals( Environment.MEDIA_MOUNTED ) ) {
			textView.setText( "No se ha podido montar la tarjeta" ) ;
		}
		
		else {
			File externalDir = Environment.getExternalStorageDirectory() ; //Obtenemos la ruta donde se encuentra
			File textFile = new File( externalDir.getAbsolutePath() + File.separator + "text.txt" ) ; //Creamos un fichero
			
			try {
				writeTextFile( textFile, "Mensaje enviado, corto y cambio." ) ; //Escribimos en el fichero
				String text = readTextFile( textFile ) ;
				textView.setText( text ) ;
			
				if( !textFile.delete() ) {
					textView.setText( "No se ha podido eliminar el archivo temporal" ) ;
				}
			}
			
			catch ( IOException e ) {
				textView.setText( "Se ha producido una excepcion" + e.getMessage() ) ;
			}
		}
	}
	
	private void writeTextFile( File file, String text ) throws IOException  { //Metodo para la escritura
		BufferedWriter writer = new BufferedWriter( new FileWriter( file ) ) ;
		writer.write( text ) ;
		writer.close() ;
	}
	
	private String readTextFile( File file ) throws IOException { //Metodo para la lectura
		BufferedReader reader = new BufferedReader( new FileReader( file ) ) ;
		StringBuilder text = new StringBuilder() ;
		String line ;
		
		while ( (  line = reader.readLine() ) != null ) {
			text.append( line ) ;
			text.append( "\n" ) ;
		}
		reader.close() ;
		return text.toString() ;
	}	
}
