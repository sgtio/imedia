package com.swcm.imedia;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Main_Page extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//Obtenemos una referencia a los controles de la interfaz
		final Button btnLogin = (Button)findViewById(R.id.login);
		final Button btnRegister = (Button)findViewById(R.id.performSearch);
		final EditText txtUser = (EditText)findViewById(R.id.user);
		final EditText txtPass = (EditText)findViewById(R.id.password);
		final CheckBox rememberMe = (CheckBox)findViewById(R.id.remember_me);
		
		//Comprueba 'Remember Me'
		final DatabaseManager dbM = new DatabaseManager(this,"Multimedia",null);
		SQLiteDatabase db = dbM.getReadableDatabase();		
//		dbM.introduceDatosMedia("El Se�or de los Anillos: El Retorno del Rey", "Las fuerzas de Saruman han sido destruidas, y su fortaleza sitiada. Ha llegado el momento de decidir el destino de la Tierra Media, y, por primera vez, parece que hay una peque�a esperanza. El inter�s del se�or oscuro Sauron se centra ahora en Gondor, el �ltimo reducto de los hombres, cuyo trono ser� reclamado por Aragorn. Sauron se dispone a lanzar un ataque decisivo contra Gondor. Mientras tanto, Frodo y Sam continuan su camino hacia Mordor, con la esperanza de llegar al Monte del Destino.", (float)4, "/storage/extSdCard/iMedia/retorno.jpg", "", "3", Main_Page.this);
//		dbM.introduceDatosMedia("Star Wars I: La Amenaza Fantasma", "Ambientada treinta a�os antes que 'La guerra de las galaxias' (1977), muestra la infancia de Darth Vader, el pasado de Obi-Wan Kenobi y el resurgimiento de los Sith, los caballeros Jedi dominados por el Lado Oscuro. La Federaci�n de Comercio ha bloqueado el peque�o planeta de Naboo, gobernado por la joven Reina Amidala; se trata de un plan ideado por Sith Darth Sidious, que, manteni�ndose en el anonimato, dirige a los neimoidianos, que est�n al mando de la Federaci�n. El Jedi Qui-Gon Jinn y su aprendiz Obi-Wan Kenobi convencen a Amidala para que vaya a Coruscant, la capital de la Rep�blica y sede del Consejo Jedi, e trate de neutralizar esta amenaza. Pero, al intentar esquivar el bloqueo, la nave real resulta averiada, vi�ndose as� obligada la tripulaci�n a aterrizar en el des�rtico y remoto planeta de Tatooine...", (float)3.1, "/storage/extSdCard/iMedia/amenaza.jpg", "", "3", Main_Page.this);
//		dbM.introduceDatosMedia("Los Simpson", "Serie de TV (1989-Actualidad). 24 temporadas. M�s de 520 episodios. Emitida por la Cadena Fox desde 1989, 'Los Simpson' son ya todo un cl�sico de la televisi�n mundial, una insuperable serie que narra las historias de una peculiar familia (Homer, Marge, Bart, Maggie y Lisa Simpson) y otros divertidos personajes de la singular localidad norteamericana de Springfield. Homer es el padre, un desastroso inspector de seguridad de la central nuclear. Marge es la madre, una ama de casa que soporta como puede todo lo que le rodea, que no es poco. Bart es un ni�o de 10 a�os que alegra su vida realizando travesuras de todo tipo. Lisa es la m�s inteligente de la familia, que siempre busca hacerse un hueco en el mundo que le rodea, y la m�s peque�a, Maggie, un beb� que todav�a no habla, pero que sin embargo da mucho que hablar. Toda una familia con una vida repleta de eventos y locuras.", (float)4.3, "/storage/extSdCard/iMedia/simpsons.jpg", "", "2", Main_Page.this);
//		dbM.introduceDatosMedia("El Pr�ncipe de Bel-Air", "Serie de TV (1990-1996). 6 temporadas. 148 episodios. Will Smith interpreta a un joven de Philadelphia que se traslada a vivir con sus ricos parientes al lujoso barrio de Bel-Air, en California. La familia la forman sus t�os y sus primos Carlton, Hilary y Ashley; adem�s est� el mayordomo Geoffrey. El incorregible y avispado Will deber� adaptarse a su nueva familia, a un nivel de vida al que no estaba acostumbrado y a las reglas de convivencia impuestas por su t�o Phil, un juez muy preocupado por su reputaci�n.", (float)3.5, "/storage/extSdCard/iMedia/principe.jpg", "", "2", Main_Page.this);
//		dbM.introduceDatosMedia("Dragon Ball Z", "Serie TV (1989-1996). 291 episodios. La historia de Dragon Ball Z se divide en cuatro ramas centrales, repartidas en un total de 291 episodios. La primera abarca desde que Son Gohan, hijo de Goku, es raptado por el saiyan Radish. La etapa de Freezer lleva a Goku, Krillin, Vegeta y Bulma al planeta Namek. Despu�s viene la Saga de los Androides, que da comienzo cuando tres androides (C-16, C-17, C-18) comienzan a aterrorizar la tierra, hasta la llegada de C�lula. La �ltima etapa de Z la cubre la Saga de Buu. Comienza cuando Babid�, un brujo bastante ambicioso, comienza un plan para resucitar al poderoso monstruo Buu, un ente capaz de poner la tierra a su favor en un par de movimientos.", (float)3.9, "/storage/extSdCard/iMedia/dragon.jpg", "", "2", Main_Page.this);
//		dbM.introduceDatosMedia("Star Wars Trilogy: BSO", "Basada en las pel�culas de la m�tica saga Star Wars, la banda sonora dirigida en su mayor medida por el gran director John Williams es una obra de arte musical que no dejar� a ning�n fan descontento.", (float)4.7, "/storage/extSdCard/iMedia/bso.jpg", "", "1", Main_Page.this);
//		dbM.introduceDatosMedia("Don Quijote de La Mancha", "Es la historia de un hidalgo de la Mancha de unos 50 a�os que tras leer muchos libros de caballer�a, un g�nero popular en siglo XVI, decide disfrazarse de caballero andante y embarcarse en una serie de aventuras con su viejo caballo Rocinante. Tiene como fin 'irse por todo el mundo con sus armas y caballo a buscar las aventuras y a ejercitarse en todo aquello que �l hab�a le�do que los caballeros andantes se ejercitaban, deshaciendo todo g�nero de agravio y poni�ndose en ocasiones y peligro donde, acab�ndolos, cobrase eterno nombre y fama' (Parte 1, Cap. 1).\n\nSiguiendo la tradici�n caballeresca, don Quijote se encomienda a Dulcinea del Toboso, una figura imaginada por el protagonista. En el mundo de Quijote, nada es lo que aparenta ser. Los molinos son gigantes, las ventas son castillos, las plebeyas son princesas, y los t�teres son moros. Durante estas andanzas los caminos de don Quijote y Sancho Panza se cruzan con otros personajes que cuentan sus historias. Pero no todas las aventuras son divertidas ni graciosas --en algunas, especialmente en la segunda parte-- don Quijote y Sancho Panza se convierten en los blancos de burlas y enga�os. Al final, don Quijote ya no es el personaje c�mico y burlesco. Vencido por el desega�o, nuestro protagonista recupera la cordura pero pierde la vida.", (float)4.7, "/storage/extSdCard/iMedia/quijote.jpg", "", "0", Main_Page.this);

		try { //Debemos poner el try porque la primera vez que se ejecuta la tabla remember no tiene por que estar creada
			Cursor c = db.rawQuery("SELECT * FROM remember", null);
			if (c.moveToFirst()) {
				String user_rem = c.getString(c.getColumnIndex("user"));
				String pass_rem = c.getString(c.getColumnIndex("pass"));
				txtUser.setText(user_rem, TextView.BufferType.EDITABLE);
				txtPass.setText(pass_rem, TextView.BufferType.EDITABLE);
				
				if (c.getInt(c.getColumnIndex("recordar")) == 1) {
					rememberMe.setChecked(true);
				}
				else {
					rememberMe.setChecked(false);
				}
				c.close();
			}
		} catch (Exception e) {Log.w("REMEMBER","La tabla no existe");}
		
		//Implementamos el evento click del boton
        btnLogin.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {
        		//Creamos el Intent
                Intent i = new Intent(Main_Page.this, Index.class);

                String user = txtUser.getText().toString();
                String pass = txtPass.getText().toString();
                
                //Comprobar que el user y pass son correctos, si lo son:
                SQLiteDatabase db = dbM.getReadableDatabase();
                
                if(db.rawQuery("SELECT * FROM users WHERE user=? AND password=?", new String[]{user,pass}).getCount()>0){
         			//Creamos la informacion a pasar entre actividades
                    db.close();
                	Bundle b = new Bundle();
                    b.putString("USER", user);
                    
                    //Remember Me
                    if(rememberMe.isChecked()) {
                    	dbM.insertRemember(user,pass,1);
                    }
                    else {
                    	dbM.insertRemember("","",0);
                    }
                     
                    //Anadimos la informacion al intent
                    i.putExtras(b);
  
                    //Iniciamos la nueva actividad
                    startActivity(i);
                    
                    //La terminamos para que no se guarde en la pila
                    finish();
                }
         		else {
         			db.close();
         			Toast.makeText(Main_Page.this, R.string.userPassError, Toast.LENGTH_SHORT).show();
         		}
			}
        });
        
        btnRegister.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {
                 Intent i = new Intent(Main_Page.this, Register.class);
                 startActivityForResult(i, 0);
			}
        });
        }
	
}
