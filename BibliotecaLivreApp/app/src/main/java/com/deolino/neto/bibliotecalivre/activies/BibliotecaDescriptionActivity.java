package com.deolino.neto.bibliotecalivre.activies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;

/**
 * Created by neto on 18/01/17.
 */

public class BibliotecaDescriptionActivity extends AppCompatActivity implements ServerResponseListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_description_biblioteca);

        //this.serverRequest = new ServerRequest(this, this);
        //this.context = this;

        // CARREGAR O QUE FOI PASSADO NA TELA ANTERIOR
        // From CidadeDescriptionActivity


    }

    @Override
    public void onSuccess(Response response, String requestUrl) {

    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }
}
