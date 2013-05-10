package com.swcm.imedia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Help extends Activity {
	private final String about_message="IMedia v1.0 (beta)" +
			"\n date: 2013 \n Authors:\n\n - Enrique García Gutierrez\n" +
			" - Sergio Ruiz Peña \n\n\n Thank you for using our app";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.help);
	    final TextView helpText = (TextView) findViewById(R.id.helpText);
	    final Button buttonAbout =(Button) findViewById(R.id.buttonAbout);
	    // Ponemos el texto y permitimos scroll
	    helpText.setText(R.string.help);
	    helpText.setScrollbarFadingEnabled(true);
		helpText.setMovementMethod(new ScrollingMovementMethod());
		
		// ActionListener del boton
		buttonAbout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Mostramos el mensaje de about
				AlertDialog about = new AlertDialog.Builder(Help.this).create();
				about.setCancelable(false); //Quitamos el boton de BACK
				about.setTitle(R.string.about_us); //Titulo
				about.setMessage(about_message); //Mensaje
				about.setButton("OK", new DialogInterface.OnClickListener() {
					// Añadimos el boton OK para que al pulsar se cierre
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						
					}
				});
				about.show();
			}
		});
	}

}
