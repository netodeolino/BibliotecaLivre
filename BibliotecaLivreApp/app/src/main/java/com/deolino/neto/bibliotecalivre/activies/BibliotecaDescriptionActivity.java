package com.deolino.neto.bibliotecalivre.activies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.constants.Constants;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.model.Biblioteca;
import com.deolino.neto.bibliotecalivre.model.Livro;
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

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

    private int bibliotecaCod;

    private ArrayList<Livro> livros = new ArrayList<Livro>();

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
        this.bibliotecaCod = intent.getIntExtra("bibliotecaCod", 0);

        serverRequest.get(ServerRequest.FIND_BIBLIOTECA_BY_CIDADE, bibliotecaCod);

        this.listViewLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(Constants.LOG_TEST, "Item " + position + " clicked");
            }
        });
    }

    @Override
    public void onSuccess(Response response, String requestUrl) {
        if (requestUrl.equals(ServerRequest.FIND_BIBLIOTECA_BY_CIDADE)) {
            if (response.getResult()) {
                ArrayList<LinkedTreeMap<String, Object>> data = (ArrayList<LinkedTreeMap<String, Object>>) response.getData();
                try {
                    for (LinkedTreeMap<String, Object> mp : data) {
                        Biblioteca b = new Biblioteca();

                        b.setNome(mp.get("nome").toString());

                        this.biblioteca = b;
                    }
                    updateActivityDescription();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(Constants.LOG_TAG, "LOL--> " + e.toString());
                }
            }
        } else if () {

        } else {
            Log.d(Constants.LOG_TEST, "REQUEST NÃO TRATADO AINDA :)");
        }
    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }

    private void updateActivityDescription() {
        // AJEITAR A IMAGEM, ESTÁ UMA FIXA
        this.imageViewBiblioteca.setImageResource(R.drawable.biblioteca_icon);
        this.textViewNome.setText(biblioteca.getNome());

        serverRequest.get(ServerRequest.FIND_LI);
    }
}
