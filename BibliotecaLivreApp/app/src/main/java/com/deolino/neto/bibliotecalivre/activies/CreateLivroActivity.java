package com.deolino.neto.bibliotecalivre.activies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.constants.Constants;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.model.Livro;
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;

/**
 * Created by neto on 10/01/17.
 */

public class CreateLivroActivity extends AppCompatActivity implements ServerResponseListener {

    private EditText editTextNome;
    private EditText editTextAno;
    private EditText editTextISBN;
    private EditText editTextAutor;
    private EditText editTextCategoria;

    private Button button;

    private Livro livro;

    private ServerRequest serverRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_livro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.editTextNome = (EditText) findViewById(R.id.etCreateName);
        this.editTextAno = (EditText) findViewById(R.id.etCreateAno);
        this.editTextISBN = (EditText) findViewById(R.id.etCreateISBN);
        this.editTextAutor = (EditText) findViewById(R.id.etCreateAutor);
        this.editTextCategoria = (EditText) findViewById(R.id.etCreateCategoria);

        this.livro = new Livro();
        this.serverRequest = new ServerRequest(this, this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void createLivro (View view) {
        livro.setNome(editTextNome.getText().toString());
        livro.setAno(Integer.parseInt(editTextAno.getText().toString()));
        livro.setISBN(editTextISBN.getText().toString());
        livro.setAutor(editTextAutor.getText().toString());
        livro.setCategoria(editTextCategoria.getText().toString());

        serverRequest.post(ServerRequest.SAVE_LIVRO, livro);
    }

    @Override
    public void onSuccess(Response response, String requestUrl) {
        if (requestUrl.equals(ServerRequest.SAVE_LIVRO)) {
            if (response.getResult()) {
                if (response.getData() != null) {
                    //LinkedTreeMap<String, Object> data = (LinkedTreeMap<String, Object>) response.getData();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);

                    Toast.makeText(this, "Livro cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Não foi possível cadastrar o livro!", Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d(Constants.LOG_TEST, "NAO FOI UM SAVE LIVRO :)");
        }
    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }
}
