package com.deolino.neto.bibliotecalivre.activies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.model.Cidade;
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;

/**
 * Created by neto on 16/01/17.
 */

public class CidadeDescriptionActivity extends AppCompatActivity implements ServerResponseListener {

    private TextView textViewNome;
    private TextView textViewEstado;

    private ImageView imageViewCidade;

    private ServerRequest serverRequest;

    private Cidade cidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_cidade);

        this.imageViewCidade = (ImageView) findViewById(R.id.ivCidadeImage);
        this.textViewNome = (TextView) findViewById(R.id.tvDescriptionName);
        this.textViewEstado = (TextView) findViewById(R.id.tvDescriptionEstado);

        this.cidade = new Cidade();
        this.serverRequest = new ServerRequest(this, this);
    }

    @Override
    public void onSuccess(Response response, String requestUrl) {

    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }
}
