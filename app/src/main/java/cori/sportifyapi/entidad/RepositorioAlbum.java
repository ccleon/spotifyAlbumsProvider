package cori.sportifyapi.entidad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static cori.sportifyapi.entidad.AlbumContract.*;

public class RepositorioAlbum extends SQLiteOpenHelper{

    private static final String DB_NAME = tablaAlbum.TABLE_NAME + ".db";
    private static final int DB_VERSION = 1;

    public RepositorioAlbum (Context context){
        super (context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String consultaSQL = "CREATE TABLE " + tablaAlbum.TABLE_NAME + "("
                + tablaAlbum.COL_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tablaAlbum.COL_NAME_NAME + " TEXT,"
                + tablaAlbum.COL_NAME_TYPE + " TEXT,"
                + tablaAlbum.COL_NAME_LARGE_IMAGE + " TEXT,"
                + tablaAlbum.COL_NAME_SMALL_IMAGE + " TEXT" + ")";
        db.execSQL(consultaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String consultaSQL = "DROP TABLE IF EXISTS " +tablaAlbum.TABLE_NAME;
        db.execSQL(consultaSQL);
        onCreate(db);
    }

    public long add (String name, String type, String bigImage, String smallImage){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(tablaAlbum.COL_NAME_NAME, name);
        valores.put(tablaAlbum.COL_NAME_TYPE, type);
        valores.put(tablaAlbum.COL_NAME_LARGE_IMAGE, bigImage);
        valores.put(tablaAlbum.COL_NAME_SMALL_IMAGE, smallImage);

        return db.insert(tablaAlbum.TABLE_NAME, null, valores);
    }

    public int count (){
        SQLiteDatabase db = this.getReadableDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, tablaAlbum.TABLE_NAME);
        return count;
    }

    public ArrayList<EntidadAlbum> getAll(){
        ArrayList<EntidadAlbum> lista = new ArrayList<EntidadAlbum>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery( "select * from "+tablaAlbum.TABLE_NAME, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            EntidadAlbum entidadAlbum = new EntidadAlbum(
                    cursor.getInt(cursor.getColumnIndex(tablaAlbum.COL_NAME_ID)),
                    cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_NAME_NAME)),
                    cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_NAME_TYPE)),
                    cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_NAME_LARGE_IMAGE)),
                    cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_NAME_SMALL_IMAGE))
            );
            lista.add(entidadAlbum);
            cursor.moveToNext();
        }
        return lista;
    }

    public EntidadAlbum getById (int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from "+ tablaAlbum.TABLE_NAME + " where "
                + tablaAlbum.COL_NAME_ID + "=" + id, null);

        EntidadAlbum entidadAlbum = new EntidadAlbum(
                cursor.getInt(cursor.getColumnIndex(tablaAlbum.COL_NAME_ID)),
                cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_NAME_NAME)),
                cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_NAME_TYPE)),
                cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_NAME_LARGE_IMAGE)),
                cursor.getString(cursor.getColumnIndex(tablaAlbum.COL_NAME_SMALL_IMAGE))
        );
        return entidadAlbum;
    }

    public void deleteById (Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tablaAlbum.TABLE_NAME, tablaAlbum.COL_NAME_ID + " = ?", new String[]{Integer.toString(id)});
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tablaAlbum.TABLE_NAME, null, null);
    }

}
