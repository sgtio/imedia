package com.swcm.imedia;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class Edit extends Activity {

	private static final int SELECT_PHOTO = 100;
	EditText picturePath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		// Obtenemos todos los elementos de la pantalla de edicion
		final EditText nombre = (EditText) findViewById(R.id.nombre);
		picturePath = (EditText) findViewById(R.id.picturePath);
		final EditText comentarios = (EditText) findViewById(R.id.comentarios);
		final EditText descripcion = (EditText) findViewById(R.id.descripcion);
		final RatingBar rating = (RatingBar) findViewById(R.id.rate);
		final Button create = (Button) findViewById(R.id.modificar);
		final DatabaseManager dbM = new DatabaseManager(this,"Multimedia",null);
		final Bundle b = this.getIntent().getExtras();
		final String user = b.getString("USER");
		final Spinner selectCategory = (Spinner) findViewById(R.id.selectCategory);
		
		// Añadimos las opciones del adapter
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categorias,android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		selectCategory.setAdapter(adapter);
		
		// Listener para la categoria
		selectCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		          final String categoria = "" + pos;  
		        // Cuando seleccionamos un elemento, creamos el listener en el boton de send&edit 
		  		create.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						
						String name = nombre.getText().toString();
						String picture = picturePath.getText().toString();
						String comentar = comentarios.getText().toString();
						String describir = descripcion.getText().toString();
						float puntuacion = rating.getRating();
						dbM.introduceDatosMedia(name, describir, puntuacion, picture, comentar, categoria, Edit.this);
						Intent i = new Intent(Edit.this, Index.class);
						Bundle bundle = new Bundle();
						bundle.putString("USER",user);
						i.putExtras(bundle);
						finish();
						startActivity(i);
					}
				});
		          
		  		//Para elegir imagen de la galeria
				picturePath.setOnClickListener(new View.OnClickListener() {
		        	@Override
		            public void onClick(View v) {
		        		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		        		photoPickerIntent.setType("image/*");
		        		startActivityForResult(photoPickerIntent, SELECT_PHOTO); 
					}
		        });
			}	
			
			@Override
		    public void onNothingSelected(AdapterView<?> parent) {
		          final String categoria="0";
			        // Cuando no seleccionamos un elemento, creamos el listener en el boton de send&edit   
		  		create.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						
						String name = nombre.getText().toString();
						String picture = picturePath.getText().toString();
						String comentar = comentarios.getText().toString();
						String describir = descripcion.getText().toString();
						float puntuacion = rating.getRating();
						dbM.introduceDatosMedia(name,describir, puntuacion, picture, comentar, categoria, Edit.this);
						Intent i = new Intent(Edit.this, Index.class);
						Bundle bundle = new Bundle();
						bundle.putString("USER",user);
						i.putExtras(bundle);
						finish();
						startActivity(i);
						
					}
				});
		    }
		});
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 
	    switch(requestCode) { 
	    case SELECT_PHOTO: //Para elegir imagen de la galeria
	        if(resultCode == RESULT_OK){
	            Uri selectedImage = imageReturnedIntent.getData();  
	            String[] filePathColumn = {MediaStore.Images.Media.DATA};
	            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
	            cursor.moveToFirst();
	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            String filePath = cursor.getString(columnIndex);
	            cursor.close();
	            picturePath.setText(filePath, EditText.BufferType.SPANNABLE); //Creo que spannable es pa q no se pueda editar
	            //Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
	        }
	    }
	}
}
