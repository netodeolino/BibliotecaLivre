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
import com.deolino.neto.bibliotecalivre.model.Cidade;

import java.util.ArrayList;

/**
 * Created by neto on 15/01/17.
 */

public class CidadeAdapter extends ArrayAdapter<Cidade> {

    public CidadeAdapter(Context context, ArrayList<Cidade> cidades) {
        super(context, 0, cidades);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cidade cidade = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_cidade, parent, false);
        }

        TextView cidadeTV = (TextView) convertView.findViewById(R.id.tvCidadeNome);
        ImageView cidadeIV = (ImageView) convertView.findViewById(R.id.ivCidadeImage);

        cidadeTV.setText(cidade.getNome());
        cidadeIV.setImageResource(R.drawable.cidade_icon);

        return convertView;
    }
}
