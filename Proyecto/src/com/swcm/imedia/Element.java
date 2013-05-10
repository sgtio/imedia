package com.swcm.imedia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.*;

public class Element extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.element);
	    // TODO Auto-generated method stub
	    
	    Bundle b = this.getIntent().getExtras();
		String[] name = {b.getString("ELEMENT")};
		// Logica SQL
		final DatabaseManager db = new DatabaseManager(this,"Multimedia",null);
		String[] campos={"description","stars","picture","comments","category"};
		String[] datos = db.leeDatosMedia(name, campos);
		
		// Vamos poniendo los datos extraidos
		// Apertura fichero descripcion
		String description="";
		try {
			//Creamos el objeto del archivo que vamos a leer
			File f = new File(datos[0]);
			//Creamos el lector del archivo
			FileReader lectorArchivo = new FileReader(f);
			//Creamos un lector en buffer para recopilar datos a traves del flujo "lectorArchivo" que hemos creado
			BufferedReader br = new BufferedReader(lectorArchivo);
			//Esta variable "l" la utilizamos para guardar mas adelante toda la lectura del archivo
			String aux="";/*variable auxiliar*/
			aux=br.readLine();
			while(aux!=null){			
				//almacenamos lo leido en la iteracion anterior
				description=description+aux+"\n";
				// actualizamos aux
				aux=br.readLine();
			}
			br.close();
			lectorArchivo.close();
		}
		catch (Exception e){
			description = datos[0];
		}
		
		// Nombre
		final TextView elementName = (TextView) findViewById(R.id.elementName);
		elementName.setText(b.getString("ELEMENT"));
				
		// Descripcion
		final TextView describe = (TextView) findViewById(R.id.description);
		describe.setText(description);
		describe.setScrollbarFadingEnabled(true);
		describe.setMovementMethod(new ScrollingMovementMethod());
		
		// Puntuacion
		final RatingBar rating = (RatingBar)findViewById(R.id.rate);
		rating.setRating(Float.parseFloat(datos[1]));
		
		// Imagen
		final ImageView picture = (ImageView) findViewById(R.id.picturePath);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;
		Bitmap bm = BitmapFactory.decodeFile(datos[2], options);
		picture.setImageBitmap(bm);
		
		// Categoria
		final TextView category = (TextView) findViewById(R.id.categoryDisplay);
		category.setText(intToCategory(datos[4]));
		
		//Boton eliminar elemento
		final Button deleteButton = (Button) findViewById(R.id.delete);
		deleteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alert = new AlertDialog.Builder(Element.this);
				alert.setTitle(R.string.deleteTitle);
				alert.setMessage(R.string.deleteMessage);
				
				alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					  db.eliminaElementoMedia("name", elementName.getText().toString());
					  Intent i = new Intent(Element.this, Index.class);
					  Bundle bundle = new Bundle();
					  bundle.putString("USER","Prueba");
					  i.putExtras(bundle);
					  finish();
					  startActivity(i);
					  }
					});

					alert.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
					  public void onClick(DialogInterface dialog, int whichButton) {
					    // Cancelado
					  }
					});

				alert.show();
			}
		});
		

		
	}
		private int intToCategory(String categoryIndex){
			int[] categories = {R.string.books_category,
								R.string.music_category,
								R.string.shows_category,
								R.string.movies_category};
			
			return categories[Integer.parseInt(categoryIndex)];
			
			
			
			
			
		}

	
}
