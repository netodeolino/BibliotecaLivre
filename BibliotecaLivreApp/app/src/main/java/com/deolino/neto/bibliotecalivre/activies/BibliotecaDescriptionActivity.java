package com.deolino.neto.bibliotecalivre.activies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.model.Biblioteca;
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;

/**
 * Created by neto on 18/01/17.
 */

public class BibliotecaDescriptionActivity extends AppCompatActivity implements ServerResponseListener {

    private TextView textViewNome;
    private ImageView imageViewBiblioteca;

    private ListView listViewLivros;

    private Context context;

    private ServerRequest serverRequest;

    private Intent intent;

    private Biblioteca biblioteca;

    private String bibliotecaNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_biblioteca);

        this.imageViewBiblioteca = (ImageView) findViewById(R.id.ivBibliotecaImage);
        this.textViewNome = (TextView) findViewById(R.id.tvBibliotecaNome);
        this.listViewLivros = (ListView) findViewById(R.id.lvLivros);

        this.serverRequest = new ServerRequest(this, this);
        this.context = this;
        this.biblioteca = new Biblioteca();

        this.intent = getIntent();
        this.bibliotecaNome = intent.getStringExtra("bibliotecaName");

        serverRequest.get(ServerRequest.FIND_BIBLIOTECA_BY_NAME, bibliotecaNome);

    }

    @Override
    public void onSuccess(Response response, String requestUrl) {

    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }
}
