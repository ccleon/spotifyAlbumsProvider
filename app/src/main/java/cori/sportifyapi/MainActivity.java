package cori.sportifyapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import cori.sportifyapi.adapters.ResBusquedaAdapter;
import cori.sportifyapi.interfaces.*;
import cori.sportifyapi.models.*;
import retrofit.*;

import static retrofit.GsonConverterFactory.*;

public class MainActivity extends Activity {

    private static final String API_BASE_URL = "https://api.spotify.com/v1/";
    private static final String LOG_TAG = "LOG_TAG_ERROR";

    ListView list;

    private SpotifyRESTAPIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL).
                addConverterFactory(create()).build();

        apiService = retrofit.create(SpotifyRESTAPIService.class);
    }

    public void obtenerAlbumsAdapted (ArrayList<Item> itemArrayList){
        ResBusquedaAdapter adapter = new ResBusquedaAdapter(this, itemArrayList);
        list = (ListView) findViewById(R.id.texto);
        list.setAdapter(adapter);
    }

    public void obtenerAlbums (View v) {
        EditText artistName = (EditText) findViewById(R.id.edited);
        String artist = artistName.getText().toString();

        Call<AlbumsRes> call_async = apiService.searchAlbums("artist:" + artist);

        call_async.enqueue(new Callback<AlbumsRes>() {
            @Override
            public void onResponse(Response<AlbumsRes> response, Retrofit retrofit) {
                final AlbumsRes albumsRes = response.body();
                final ArrayList<Item> itemsArray = (ArrayList<Item>) albumsRes.getAlbums().getItems();
                obtenerAlbumsAdapted(itemsArray);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), LOG_TAG + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(LOG_TAG, t.getMessage());
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.verListaFavoritos:
                startActivity(new Intent(this, VerFavoritos.class));
               return true;

            case R.id.ajustes:
                Toast.makeText(this, "De momento no hay nada aquí",
                        Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}