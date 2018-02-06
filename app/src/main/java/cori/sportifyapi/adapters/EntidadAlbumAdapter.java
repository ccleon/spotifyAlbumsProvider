package cori.sportifyapi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;

import cori.sportifyapi.R;
import cori.sportifyapi.entidad.EntidadAlbum;
import cori.sportifyapi.entidad.RepositorioAlbum;

public class EntidadAlbumAdapter extends ArrayAdapter<EntidadAlbum>{
    private Context context;
    private ArrayList<EntidadAlbum> entidadAlbum;
    private RepositorioAlbum repositorioAlbum;

    public EntidadAlbumAdapter (Context context, ArrayList<EntidadAlbum> entidadAlbum) {
        super(context, R.layout.custom_list_layout, entidadAlbum);
        this.entidadAlbum = entidadAlbum;
        this.context = context;
        this.repositorioAlbum = new RepositorioAlbum(getContext());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_favoritos_layout, null);
        }

        final EntidadAlbum entidad = this.entidadAlbum.get(position);

        if (entidad != null) {

            ImageView imagenAlbum = (ImageView) convertView.findViewById(R.id.imagenFavorito);
            Picasso.with(context).load(entidad.getSmallImage()).into(imagenAlbum);

            TextView tituloFavorito = (TextView) convertView.findViewById(R.id.tituloFavorito);
            tituloFavorito.setText(entidad.getName());

            TextView tipoFavorito = (TextView) convertView.findViewById(R.id.tipoFavorito);
            tipoFavorito.setText(WordUtils.capitalize(entidad.getType()));

            Button eliminarFavorito = (Button) convertView.findViewById(R.id.eliminarFavorito);
            eliminarFavorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    repositorioAlbum.deleteById(entidadAlbum.get(position).getId());
                    entidadAlbum.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(getContext(), "Album eliminado de favoritos", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return convertView;
    }
}
