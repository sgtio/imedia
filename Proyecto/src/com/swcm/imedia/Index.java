package com.swcm.imedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Index extends Activity {

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
 
        	//Localizar los controles
            TextView txtWelcome = (TextView)findViewById(R.id.welcome);
 
            //Recuperamos la informaci�n pasada en el intent
            final Bundle b = this.getIntent().getExtras();
 
            //Construimos el mensaje a mostrar
            txtWelcome.setText(getString(R.string.welcome) + " " + b.getString("USER"));
            
            //Obtenemos una referencia a los controles de la interfaz
            final Button btnLogout = (Button)findViewById(R.id.logout);
            final ImageView imgShows = (ImageView) findViewById(R.id.picturePath);
            final ImageView imgMovies = (ImageView) findViewById(R.id.moviesimg);
            final ImageView imgMusic = (ImageView) findViewById(R.id.musicimg);
            final ImageView imgBooks = (ImageView) findViewById(R.id.booksimg);
            final ImageView imgSearch = (ImageView) findViewById(R.id.searchimg);
            final ImageView imgEdit = (ImageView) findViewById(R.id.editImg);
            final Button btnHelp = (Button)findViewById(R.id.helpButton);
            final Button btnBug = (Button) findViewById(R.id.bug_mail);
            
            final String user = b.getString("USER");
            final ScrollView scrollIndex = (ScrollView) findViewById(R.id.scrollIndex);
            scrollIndex.setVerticalScrollBarEnabled(false);
            
            //Boton Logout
            btnLogout.setOnClickListener(new View.OnClickListener() {
            	@Override
                public void onClick(View v) {
                     Intent i = new Intent(Index.this, Main_Page.class);
//                     if(b.getBoolean("REMEMBER")) {
//                    	 Bundle bund = new Bundle();
//                    	 bund.putString("USER_REM", user);
//                    	 i.putExtras(bund);
//                     }
                     startActivity(i);
                     finish();
    			}
            });
            
            //Boton About and Help
            btnHelp.setOnClickListener(new View.OnClickListener() {
            	@Override
                public void onClick(View v) {
                     Intent i = new Intent(Index.this, Help.class);
                     startActivity(i);
    			}
            });
            
            //Boton New Item
            imgEdit.setOnClickListener(new View.OnClickListener() {
            	@Override
                public void onClick(View v) {
                     Intent i = new Intent(Index.this, Edit.class);
                     Bundle b = new Bundle();
                     b.putString("USER",user);
                     i.putExtras(b);
                     startActivity(i);
    			}
            });
            
            //Boton Search
            imgSearch.setOnClickListener(new View.OnClickListener() {
            	@Override
                public void onClick(View v) {
                     Intent i = new Intent(Index.this, Search.class);
                     startActivity(i);
    			}
            });

            //Boton Shows
            imgShows.setOnClickListener(new View.OnClickListener() {
            	@Override
                public void onClick(View v) {
                     Intent i = new Intent(Index.this, Category.class);
                     Bundle b = new Bundle();
                     b.putString("CATEGORY", "Shows");
                     i.putExtras(b);
                     startActivity(i);
    			}
            });
            
            //Boton Movies
            imgMovies.setOnClickListener(new View.OnClickListener() {
            	@Override
                public void onClick(View v) {
                     Intent i = new Intent(Index.this, Category.class);
                     Bundle b = new Bundle();
                     b.putString("CATEGORY", "Movies");
                     i.putExtras(b);
                     startActivity(i);
    			}
            });
            
            //Boton Musci
            imgMusic.setOnClickListener(new View.OnClickListener() {
            	@Override
                public void onClick(View v) {
                     Intent i = new Intent(Index.this, Category.class);
                     Bundle b = new Bundle();
                     b.putString("CATEGORY", "Music");
                     i.putExtras(b);
                     startActivity(i);
    			}
            });
            
            //Boton Books
            imgBooks.setOnClickListener(new View.OnClickListener() {
            	@Override
                public void onClick(View v) {
                     Intent i = new Intent(Index.this, Category.class);
                     Bundle b = new Bundle();
                     b.putString("CATEGORY", "Books");
                     i.putExtras(b);
                     startActivity(i);
    			}
            });
            
            //Boton Bug Mail
            btnBug.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					try{
					Intent i = new Intent(Intent.ACTION_SEND);
					i.setType("text/plain");
					i.putExtra(Intent.EXTRA_EMAIL, new String[]{"sejoruiz@gmail.com"});
					i.putExtra(Intent.EXTRA_SUBJECT, "Found a bug");
					i.putExtra(Intent.EXTRA_TEXT, "");
					startActivity(Intent.createChooser(i, "Send mail..."));
					}
					catch(Exception e){
						Toast.makeText(Index.this, R.string.email, Toast.LENGTH_SHORT).show();
											
					}
					
				}
			});
    }
	
	//Codigo para tener que pulsar dos veces para salir
	private boolean doubleBackToExitPressedOnce = false;
	
	@Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.double_back, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
             doubleBackToExitPressedOnce=false;   

            }
        }, 2000);
    }
}
