package com.swcm.imedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
	
        final Button btnRegister = (Button)findViewById(R.id.btnAcceptRegister);
        final EditText regUser = (EditText)findViewById(R.id.regUser);
      	final EditText regMail = (EditText)findViewById(R.id.regEmail);
      	final EditText regPass = (EditText)findViewById(R.id.regPass);
      	final EditText repeatPass = (EditText)findViewById(R.id.repeatPass);
      	final DatabaseManager dbM = new DatabaseManager(this,"Multimedia",null);
		
        
        btnRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String usuario = regUser.getText().toString();
				String contrasena = regPass.getText().toString();
				String repiteContrasena = repeatPass.getText().toString();
				String correo = regMail.getText().toString();
				
				if(contrasena.equals(repiteContrasena)) {
					if(dbM.introduceDatosUsers(usuario, contrasena, correo, Register.this)) {
						Toast.makeText(Register.this, R.string.userCreated, Toast.LENGTH_SHORT);
						
						Intent i = new Intent(Register.this, Main_Page.class);
	                    startActivity(i);
	                    finish();
					}
					else {
						Toast.makeText(Register.this, R.string.userExists, Toast.LENGTH_SHORT);
					}
				}
				else {
					Toast.makeText(Register.this, R.string.passMismatch, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	@Override
    public void onBackPressed() {
		Intent i = new Intent(Register.this, Main_Page.class);
   		startActivity(i);
    }

}
