package cori.sportifyapi.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cori.sportifyapi.AlbumDetallado;
import cori.sportifyapi.R;
import cori.sportifyapi.entidad.EntidadAlbum;
import cori.sportifyapi.models.*;

import java.util.ArrayList;

public class ResBusquedaAdapter extends ArrayAdapter<Item> {
    private final Context contexto;
    private final ArrayList<Item> items;

    public ResBusquedaAdapter(Context context, ArrayList<Item> items) {
        super(context, -1, items);
        this.contexto = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_list_layout, parent, false);
        }

        final Item album = this.items.get(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imagenLista);

        if (album.getImages().size() > 0) {
            Picasso.with(contexto).load(album.getImages().get(0).getUrl()).placeholder(android.R.drawable.btn_default).into(imageView);
        } else {
            Picasso.with(contexto).load(album.getImages().get(album.getImages().size() - 1).getUrl()).into(imageView);
        }

        TextView name = (TextView) convertView.findViewById(R.id.albumName);
        name.setText(album.getName());

        TextView type = (TextView) convertView.findViewById(R.id.albumType);
        type.setText(album.getType());

        /**
         * AL PULSAR sobre un album, mostramos sus detalles y damos la opcion
         * a guardar en favoritos
         * */

       convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, AlbumDetallado.class);
                intent.putExtra("EntidadAlbum", new EntidadAlbum(album.getId().hashCode(),
                        album.getName(),
                        album.getType(),
                        album.getImages().get(album.getImages().size() - 1).getUrl(),
                        album.getImages().get(0).getUrl()));
                contexto.startActivity(intent);
            }
        });
        return convertView;
    }
}
