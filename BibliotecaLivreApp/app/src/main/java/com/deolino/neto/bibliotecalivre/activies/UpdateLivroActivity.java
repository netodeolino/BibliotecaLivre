package com.deolino.neto.bibliotecalivre.activies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.model.Livro;
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;

/**
 * Created by neto on 12/01/17.
 */

public class UpdateLivroActivity extends AppCompatActivity implements ServerResponseListener {

    private EditText editTextNome;
    private EditText editTextAno;
    private EditText editTextISBN;
    private EditText editTextAutor;
    private EditText editTextCategoria;

    private Livro livro;

    private ServerRequest serverRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_livro);

        this.editTextNome = (EditText) findViewById(R.id.etUpdateName);
        this.editTextAno = (EditText) findViewById(R.id.etUpdateAno);
        this.editTextISBN = (EditText) findViewById(R.id.etUpdateISBN);
        this.editTextAutor = (EditText) findViewById(R.id.etUpdateAutor);
        this.editTextCategoria = (EditText) findViewById(R.id.etUpdateCategoria);

        this.livro = new Livro();
        this.serverRequest = new ServerRequest(this, this);
    }

    @Override
    public void onSuccess(Response response, String requestUrl) {

    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }
}
