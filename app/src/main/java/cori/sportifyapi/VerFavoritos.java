package cori.sportifyapi;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cori.sportifyapi.entidad.EntidadAlbum;
import cori.sportifyapi.entidad.RepositorioAlbum;
import cori.sportifyapi.adapters.EntidadAlbumAdapter;

public class VerFavoritos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_favoritos);

        RepositorioAlbum repositorioAlbum = new RepositorioAlbum(getApplicationContext());
        ArrayList <EntidadAlbum> lista = repositorioAlbum.getAll();

        EntidadAlbumAdapter entidadAlbumAdapter = new EntidadAlbumAdapter(VerFavoritos.this, lista);

        ListView listaFavoritos = (ListView) findViewById(R.id.verFavoritos);
        listaFavoritos.setAdapter(entidadAlbumAdapter);

    }

}
