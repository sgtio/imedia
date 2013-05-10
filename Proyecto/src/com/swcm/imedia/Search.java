package com.swcm.imedia;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class Search extends TabActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.search);
	    // TODO Auto-generated method stub
	            
	    Resources res = getResources();
	    
	    TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
	    tabs.setup();
	    
	    //Tab All
	    TabHost.TabSpec spec = tabs.newTabSpec("All");
	    spec.setContent(R.id.tabAll);
	    spec.setIndicator(getString(R.string.allTab),
	    res.getDrawable(android.R.drawable.ic_btn_speak_now));
	    tabs.addTab(spec);
	    
	    //Tab Music
	    spec = tabs.newTabSpec("Music");
	    spec.setContent(R.id.tabMusic);
	    spec.setIndicator(getString(R.string.musicTab),
	    res.getDrawable(android.R.drawable.ic_dialog_map));
	    tabs.addTab(spec);
	    
	    //Tab Movies
	    spec = tabs.newTabSpec("Movies");
	    spec.setContent(R.id.tabMovies);
	    spec.setIndicator(getString(R.string.moviesTab),
	    res.getDrawable(android.R.drawable.ic_dialog_map));
	    tabs.addTab(spec);
	     
	    //Tab Shows
	    spec = tabs.newTabSpec("Shows");
	    spec.setContent(R.id.tabShows);
	    spec.setIndicator(getString(R.string.showsTab),
	    res.getDrawable(android.R.drawable.ic_dialog_map));
	    tabs.addTab(spec);
	    
	    //Tab Books
	    spec = tabs.newTabSpec("Books");
	    spec.setContent(R.id.tabBooks);
	    spec.setIndicator(getString(R.string.booksTab),
	    res.getDrawable(android.R.drawable.ic_dialog_map));
	    tabs.addTab(spec);
	    
	    tabs.setCurrentTab(0);
	    
	    // Vamos a rellenar datos!
	    
	    final ListView listaBooks = (ListView) findViewById(R.id.tabBooks);
	    final ListView listaMusic = (ListView) findViewById(R.id.tabMusic);
	    final ListView listaShows = (ListView) findViewById(R.id.tabShows);
	    final ListView listaMovies = (ListView) findViewById(R.id.tabMovies);
	    final ListView listaAll = (ListView) findViewById(R.id.tabAll);
        final AutoCompleteTextView search = (AutoCompleteTextView) findViewById(R.id.searchBar);
        final Button performSearch = (Button) findViewById(R.id.performSearch);
        
        performSearch.setOnClickListener(new View.OnClickListener() {
			// Al hacer click en el boton de buscar, realizamos el query en la db
        	// Ademas, ponemos a la escucha por si pulsamos un elemento
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		        DatabaseManager dbM = new DatabaseManager(Search.this,"Multimedia",null);
		        String nombre = search.getText().toString();
		        //**************
		        //Empezamos listando la categoria Books
		        ponElementos(nombre,"0",listaBooks,dbM);
		        
		        //*************
		        //Ahora listamos la categoria Music
		        ponElementos(nombre,"1",listaMusic,dbM);
		        
		        //*************
		        //Ahora listamos la categoria Shows
		        ponElementos(nombre,"2",listaShows,dbM);
		        
		        
		        //*************
		        //Ahora listamos la categoria Movies
		        ponElementos(nombre,"3",listaMovies,dbM);
		        
		        
		        //*************
		        //Ahora listamos la categoria All
		        ponElementos(nombre,"4",listaAll,dbM);
			}
		});    
        
	    
	}
	
	/**
	 * Introduce elemntos en las listas en funcion de la categoria
	 * @param nombre
	 * @param categoria
	 * @param lista
	 * @param dbM
	 */
	private void ponElementos(String nombre, String categoria, ListView lista, DatabaseManager dbM){
		String[] list;
		if(!categoria.equals("4")){
			list=dbM.listaCamposMedia(new String[] {"name"}, "name LIKE ? AND category=?",new String[] {"%"+nombre+"%",categoria},null);
		}
		else{
			list=dbM.listaCamposMedia(new String[] {"name"}, "name LIKE ?",new String[] {"%"+nombre+"%"},null);
		}
		
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Search.this, android.R.layout.simple_list_item_1, list);
		lista.setAdapter(adapter);
		lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Cambiamos a la vista de elemento						
				Intent i = new Intent(Search.this, Element.class);
				String item = ((TextView)view).getText().toString();
				Bundle b = new Bundle();
				b.putString("ELEMENT", item);
                i.putExtras(b);
                startActivity(i);		
				}
        });
		
		
	}

}
