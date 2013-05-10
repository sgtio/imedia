package com.swcm.imedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Category extends Activity {
	private final String[] categorias = {"Books", "Music", "Shows", "Movies"};
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
	
        TextView category = (TextView)findViewById(R.id.txtCategory);

        Bundle b = this.getIntent().getExtras();
        category.setText(b.getString("CATEGORY"));        
        //Identificamos la lista y mostramos los elemento que contiene
        final ListView itemList = (ListView) findViewById(R.id.itemsList);

        // Vamos a ver que categoria hemos solicitado
        int catSelected=0;
        while(!categorias[catSelected].equalsIgnoreCase(b.getString("CATEGORY"))){
        	catSelected++;    
        	if(catSelected>=categorias.length){break;}
        }
        
        //Obtenemos todos los elementos de la tabla SQL (Cursor)
        DatabaseManager dbM = new DatabaseManager(this, "Multimedia",null);
        String[] list = dbM.listaCamposMedia(new String[] {"name"},"category=?" ,new String[] {""+catSelected},null);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        itemList.setAdapter(adapter);
        //Listener al seleccionar un elemento de la lista
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent(Category.this, Element.class);
				String item = ((TextView)view).getText().toString();
				Bundle b = new Bundle();
				b.putString("ELEMENT", item);
                i.putExtras(b);
                startActivity(i);
				
			}
        });
	}
}
