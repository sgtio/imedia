package com.swcm.imedia;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
/**
 * Explicacion de la base de datos:
 * - categorias:
 * 		0: Books
 * 		1: Music
 * 		2: Shows
 * 		3: Movies
 * 		Luego si buscamos elementos cuya categoria sea menor de 4, buscaremos
 * 		en todas las categorias
 *
 */
public class DatabaseManager extends SQLiteOpenHelper  {
	// Control de version de la base de datos (constante global)
	public static final int databaseVersion = 5;
	/**
	 * 
	 * @param context
	 * @param nombre
	 * @param factory
	 */
	public DatabaseManager(Context context, String nombre, CursorFactory factory) {
		 super(context, nombre, factory, databaseVersion);
	}
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL("create table media(name text, description text, stars real, picture text, comments text, category integer)");
		db.execSQL("create table users(user text, password text, email text)");
		db.execSQL("create table remember(user text, pass text, recordar integer)");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		//Destruimos la tabla antigua
		db.execSQL("DROP TABLE IF EXISTS media");
		db.execSQL("DROP TABLE IF EXISTS users");
		db.execSQL("DROP TABLE IF EXISTS remember");
		//Reconostruimos la tabla
		db.execSQL("create table media(name text, description text, stars real, picture text, comments text, category integer)");
		db.execSQL("create table users(user text, password text, email text)");
		db.execSQL("create table remember(user text, pass text, recordar integer)");
	}
	
	/**
	 * Introduce datos en nuestra bbdd
	 * @param nombre
	 * @param descripcion
	 * @param puntuacion
	 * @param portada
	 * @param comentarios
	 * @param categoria 0: Books, 1: Music, 2: Shows, 3: Movies
	 * @param context
	 */
	public void introduceDatosMedia(String nombre, String descripcion, float puntuacion, String portada, String comentarios, String categoria, Context context){
		ContentValues valores = new ContentValues(); 
		SQLiteDatabase db = getReadableDatabase();
		String[] name = new String[1];
		name[0]=nombre;
		//Si no hay en la db un elemento con el mismo nombre, creamos la nueva entrada
		if(db.query("media", new String[] {"name"}, "name=?", name, null, null, null).getCount()<=0){
		valores.put("name", nombre);
		valores.put("description", descripcion);
		valores.put("stars", puntuacion);
		valores.put("picture", portada);
		valores.put("comments", comentarios);
		valores.put("category", categoria);
 		db.insert("media", null, valores);
 		Toast.makeText(context, R.string.datasent, Toast.LENGTH_SHORT).show();
		}
		else {Toast.makeText(context, R.string.datanotsent, Toast.LENGTH_SHORT).show();}
		db.close();
	}
	
	/**
	 * Devuelve los datos correspondientes a la fila y columnas pedidas de la base de datos
	 * @param name Fila en la que queremos buscar
	 * @param campos Columnas que deseamos que nos devuelva
	 * @return String[] con las columnas pedidas
	 */
	public String[] leeDatosMedia(String[] name, String[] campos){
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query("media", campos, "name=?", name, null, null, null);
		String[] queryDevuelto = new String[5];
		if(c.moveToFirst()){
			for(int i=0; i<c.getColumnCount();i++){			
			queryDevuelto[i]=c.getString(i);
			}
		}
		db.close();
		return queryDevuelto;
	}
	
	/**
	 * Devuelve las columnas de la base de datos correspondientes a los campos pasados como argumento.
	 * @param cols 
	 * @return String[] con las columnas correspondientes
	 */
	public String[] listaCamposMedia(String[] cols, String selection, String[] selectionArgs, String orderBy){
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.query("media", cols, selection, selectionArgs, null, null, null);
		String[] list = new String[c.getCount()];
        int i=0;
        if(c.moveToFirst()){
        	list[0]=c.getString(0);
        	while(c.moveToNext()) {	
        		i++;
        		list[i]= c.getString(0);
        	}      	
        }
        db.close();
        return list;
	}
	public void eliminaElementoMedia(String campo, String nombre){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("DELETE FROM media WHERE "+campo+"=?", new String[]{nombre});
		db.close();
		
		
	}
	public boolean introduceDatosUsers(String usuario, String contrasena, String correo, Context context){
		ContentValues valores = new ContentValues(); 
		SQLiteDatabase db = getWritableDatabase();
		
		//Si no hay en la db un elemento con el mismo nombre, creamos la nueva entrada
		if(db.query("users", new String[] {"user"}, "user=? OR email=?", new String[] {usuario,correo}, null, null, null).getCount()<=0){
			valores.put("user", usuario);
			valores.put("password", contrasena);
			valores.put("email", correo);
			db.insert("users", null, valores);
			db.close();
			Toast.makeText(context, R.string.datasent, Toast.LENGTH_SHORT).show();
			return true;
		}
		else {
			db.close();
			Toast.makeText(context, R.string.userExists, Toast.LENGTH_SHORT).show();
			return false;
		}
	}
	
	/**
	 * Actualizar datos de la tabla 'remember'
	 * @param usuario a recordar
	 * @param conrasena a recordar
	 * @param recordar 1 si 'Remember Me' esta marcado o 0 si no lo esta
	 */
	public void insertRemember(String usuario, String contrasena, int recordar){
		ContentValues valores = new ContentValues(); 
		SQLiteDatabase db = getWritableDatabase();
		
		valores.put("user", usuario);
		valores.put("pass", contrasena);
		valores.put("recordar", recordar);
		if (db.rawQuery("SELECT * FROM remember", null).getCount()>0) {
			db.update("remember", valores, null, null);
		}
		else {
			db.insert("remember", null, valores);
		}
		db.close();
	}
}
