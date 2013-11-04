package com.example.npi.ejemplos;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Bitmaps extends Activity  {

	class RenderView extends View {
		Bitmap bob565 ;
		Bitmap bob4444 ;
		Rect dst = new Rect() ;
		
		public RenderView( Context context ) {
			super( context ) ;
			
			try {
				AssetManager assetManager = context.getAssets() ; //Obtenemos los assets
				InputStream inputStream = assetManager.open( "imagenes/47825.png" ) ;
				bob565 = BitmapFactory.decodeStream( inputStream ) ; //Cargamos la imagen
				Log.d("BitmapText", "47825.png format: " + bob565.getConfig() ) ;
				inputStream = assetManager.open( "imagenes/47825.png" ) ;
				BitmapFactory.Options options = new BitmapFactory.Options() ;
				options.inPreferredConfig = Bitmap.Config.ARGB_4444 ; 
				bob4444 = BitmapFactory.decodeStream( inputStream, null, options ) ; //Cargamos la imagen con otro formato
				inputStream.close() ;
				Log.d("BitmapText", "47525.png format: " + bob4444.getConfig() ) ;
			}
			
			catch ( IOException e ) {
			}
			
			finally {
			}
		}
		
		protected void onDraw( Canvas canvas ) {
			dst.set( 50, 50, 350, 350 ) ;
			canvas.drawBitmap( bob565, null, dst, null ) ; //Dibujamos ambas imagenes
			canvas.drawBitmap(bob4444, 100, 100, null ) ;
			invalidate() ;
		}
	}
	
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState ) ;
		requestWindowFeature( Window.FEATURE_NO_TITLE ) ; //Pantalla Completa 
		getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN ) ; //Pantalla Completa
		setContentView( new RenderView( this ) ) ; 
	}
}