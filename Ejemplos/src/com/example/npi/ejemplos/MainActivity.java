package com.example.npi.ejemplos;

import com.example.npi.ejemplos.R; 

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	
	String tests[] ={ "CicloVida", "UniTactil", "MultiTactil", "Tecla", "Acelerometro", "Assets", "AlmacenamientoExterno", "Sonidos", "Multimedia", 
			"PantallaCompleta", "Render", "Geometrias", "Bitmaps", "Surface" } ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter( new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, tests ) ) ; //creamos una ListView con las clases para los ejemplos
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    protected void onListItemClick( ListView list, View view, int position, long id ) {
    	super.onListItemClick(list, view, position, id ) ;
    	String testName= tests[position] ;
    	
    	try {
    		Class clazz =  Class.forName("com.example.npi.ejemplos." + testName ) ;
    		Intent intent = new Intent( this, clazz ) ;
    		startActivity( intent ) ;
    	}
    	catch (ClassNotFoundException e ) {
    		e.printStackTrace() ;
    		
    	}
    }

    
}
