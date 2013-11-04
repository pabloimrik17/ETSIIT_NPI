package com.example.npi.ejemplos;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Acelerometro extends Activity implements SensorEventListener {
	//Archivos donde guardaremos y mostraremos la informacion
	TextView textView ;
	StringBuilder builder = new StringBuilder() ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState ) ; 
		textView = new TextView(this) ; 
		setContentView(textView) ; //Indicamos que el contenido sera proporcionado por nuestra textview
		SensorManager manager = (SensorManager)getSystemService( Context.SENSOR_SERVICE ) ; //Obtenemos los sensores del dispositivo
		
		if ( manager.getSensorList( Sensor.TYPE_ACCELEROMETER).size() == 0 ) { //Si no se encuentra ninguno de tipo acelerometro
			textView.setText( "No hay ningun acelerometro instalado" ) ;
		}
		else {
			Sensor accelerometer = manager.getSensorList( Sensor.TYPE_ACCELEROMETER ).get(0) ; //Obtenemos el acelerometro
			
			if ( !manager.registerListener( this, accelerometer, SensorManager.SENSOR_DELAY_GAME ) ) { //Comprobamos que responda
				textView.setText( "No se ha podido conectar con el acelerometro" ) ;
			}
		}
	}
	
	@Override
	public void onSensorChanged( SensorEvent event ) { //Cada vez que se produzca un cambio guardamos sus coordenadas y las imprimimos.
		builder.setLength( 0 ) ; 
		builder.append( "x: " ) ;
		builder.append( event.values[0] ) ;
		builder.append( ", y: " ) ;
		builder.append( event.values[1] ) ;
		builder.append( ", z: " ) ;
		builder.append( event.values[2] ) ;
		textView.setText(builder.toString()) ;
	}
	
	@Override 
	public void onAccuracyChanged( Sensor sensor, int accuracy ) {
	
	}
}
