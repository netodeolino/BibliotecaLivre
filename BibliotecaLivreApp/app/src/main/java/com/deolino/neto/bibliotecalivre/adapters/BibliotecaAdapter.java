package com.deolino.neto.bibliotecalivre.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.deolino.neto.bibliotecalivre.model.Biblioteca;

import java.util.ArrayList;

/**
 * Created by neto on 18/01/17.
 */

public class BibliotecaAdapter  extends ArrayAdapter<Biblioteca> {

    public BibliotecaAdapter(Context context, ArrayList<Biblioteca> bibliotecas) {
        super(context, 0, bibliotecas);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Biblioteca biblioteca = getItem(position);


        return convertView;
    }
}
