package cori.sportifyapi;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import cori.sportifyapi.entidad.AlbumContract;
import cori.sportifyapi.entidad.RepositorioAlbum;

public class AlbumProvider extends ContentProvider {

    private static final String uri = "content://cori.spotifyapi.provider/entidadalbum";
    public static final Uri CONTENT_URI = Uri.parse(uri);

    private static final int ENTIDAD = 1;
    private static final int ENTIDAD_ID = 2;

    private RepositorioAlbum repositorioAlbum;

    private static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("cori.spotifyapi.provider", "entidadalbum", ENTIDAD);
        uriMatcher.addURI("cori.spotifyapy.provider", "entidadalbum/#", ENTIDAD_ID);
    }

    @Override
    public boolean onCreate() {
        repositorioAlbum = new RepositorioAlbum(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String where = selection;
        if (uriMatcher.match(uri) == ENTIDAD_ID){
            where = AlbumContract.tablaAlbum.COL_NAME_ID + uri.getLastPathSegment();
        }

        SQLiteDatabase db = repositorioAlbum.getReadableDatabase();

        Cursor cursor = db.query(AlbumContract.tablaAlbum.TABLE_NAME, projection, where, selectionArgs, null, null, sortOrder);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);

        switch (match){
            case ENTIDAD:
                return "vnd.android.cursor.dir/vnd.cori.spotifyapi.provider.entidad";
            case ENTIDAD_ID:
                return "vnd.android.cursor.item/vnd.cori.spotifyapi.provider.entidad";
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long regId = 1;

        SQLiteDatabase db = repositorioAlbum.getWritableDatabase();

        regId = db.insert(AlbumContract.tablaAlbum.TABLE_NAME, null, values);

        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);

        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int contador;

        String where = selection;
        if (uriMatcher.match(uri) == ENTIDAD_ID){
            where = AlbumContract.tablaAlbum.COL_NAME_ID + uri.getLastPathSegment();
        }

        SQLiteDatabase db = repositorioAlbum.getWritableDatabase();

        contador = db.delete(AlbumContract.tablaAlbum.TABLE_NAME, where, selectionArgs);

        return contador;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int contador;

        String where = selection;
        if (uriMatcher.match(uri) == ENTIDAD_ID){
            where = AlbumContract.tablaAlbum.COL_NAME_ID + uri.getLastPathSegment();
        }

        SQLiteDatabase db = repositorioAlbum.getWritableDatabase();

        contador = db.update(AlbumContract.tablaAlbum.TABLE_NAME, values, where, selectionArgs);

        return contador;
    }
}
