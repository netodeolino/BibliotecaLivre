package com.deolino.neto.bibliotecalivre.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.model.Livro;

import java.util.ArrayList;

/**
 * Created by neto on 22/01/17.
 */

public class LivroAdapter extends ArrayAdapter<Livro> {

    public LivroAdapter(Context context, ArrayList<Livro> livros) {
        super(context, 0, livros);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Livro livro = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_livro, parent, false);
        }



        return convertView;
    }
}
