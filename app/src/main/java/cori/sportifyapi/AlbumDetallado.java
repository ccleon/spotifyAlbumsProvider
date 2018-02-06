package cori.sportifyapi;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import cori.sportifyapi.entidad.EntidadAlbum;
import cori.sportifyapi.entidad.RepositorioAlbum;

public class AlbumDetallado extends Activity {

    private EntidadAlbum entidadAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detallado);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        entidadAlbum = (EntidadAlbum) bundle.get("EntidadAlbum");

        ImageView imageView = (ImageView) findViewById(R.id.portadaAlbum);
        Picasso.with(getApplicationContext()).load(entidadAlbum.getLargeImage())
                .fit().centerCrop().into(imageView);

        TextView textView = (TextView) findViewById(R.id.tituloAlbum);
        textView.setText(entidadAlbum.getName());

        TextView textView2 = (TextView) findViewById(R.id.tipoAlbum);
        textView2.setText(entidadAlbum.getType());
    }

    public void añadirFavorito (View view){
        RepositorioAlbum repositorioAlbum = new RepositorioAlbum(getApplicationContext());
        repositorioAlbum.add(entidadAlbum.getName(), entidadAlbum.getType(),
                entidadAlbum.getSmallImage(), entidadAlbum.getLargeImage());
        Toast.makeText(getApplicationContext(), "Album añadido a favoritos", Toast.LENGTH_SHORT).show();
    }

}
