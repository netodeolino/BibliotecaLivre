package com.deolino.neto.bibliotecalivre.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.model.Biblioteca;

import java.util.ArrayList;

/**
 * Created by neto on 18/01/17.
 */

public class BibliotecaAdapter extends ArrayAdapter<Biblioteca> {

    public BibliotecaAdapter(Context context, ArrayList<Biblioteca> bibliotecas) {
        super(context, 0, bibliotecas);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Biblioteca biblioteca = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_biblioteca, parent, false);
        }

        TextView bibliotecaTvNome = (TextView) convertView.findViewById(R.id.tvBibliotecaNome);
        TextView bibliotecaTvEndereco = (TextView) convertView.findViewById(R.id.tvBibliotecaEndereco);
        ImageView bibliotecaIv = (ImageView) convertView.findViewById(R.id.ivBibliotecaImage);

        bibliotecaTvNome.setText(biblioteca.getNome());
        bibliotecaTvEndereco.setText(biblioteca.getEndereco());
        bibliotecaIv.setImageResource(R.drawable.biblioteca_icon);

        return convertView;
    }
}
