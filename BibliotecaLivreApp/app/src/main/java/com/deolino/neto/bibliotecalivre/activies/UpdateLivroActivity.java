package com.deolino.neto.bibliotecalivre.activies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.constants.Constants;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.model.Livro;
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

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

    private String ISBNAntigoLivro;

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

        // PARA TEST DE TELA E AFINS DE DESCRIÇAO E ATUALIZAÇAO
        serverRequest.get(ServerRequest.FIND_LIVRO_BY_NAME, "celular"); // para teste
    }

    @Override
    public void onSuccess(Response response, String requestUrl) {
        if (requestUrl.equals(ServerRequest.FIND_LIVRO_BY_NAME)) {
            if (response.getResult()) {
                ArrayList<LinkedTreeMap<String, Object>> data = (ArrayList<LinkedTreeMap<String, Object>>) response.getData();
                try {
                    for (LinkedTreeMap<String, Object> mp : data) {
                        Livro liv = new Livro();

                        liv.setNome(mp.get("nome").toString());
                        liv.setAno((int) Double.parseDouble(mp.get("ano").toString()));
                        liv.setISBN(mp.get("ISBN").toString());
                        liv.setAutor(mp.get("autor").toString());
                        liv.setCategoria(mp.get("categoria").toString());

                        this.livro = liv;

                        this.ISBNAntigoLivro = livro.getISBN();
                        updateActivityUpdate();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(Constants.LOG_TAG, "LOL--> " + e.toString());
                }
            } else {
                Toast.makeText(this, "Não foi possível realizar a operação!", Toast.LENGTH_LONG).show();
            }
        } else if (requestUrl.equals(ServerRequest.UPDATE_LIVRO)) {
            if (response.getResult()) {
                if (response.getData() != null) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);

                    Toast.makeText(this, "Livro atualizado com sucesso!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Não foi possível realizar a operação!", Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d(Constants.LOG_TEST, "REQUEST NÃO TRATADO AINDA :)");
        }
    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }

    private void updateActivityUpdate() {
        this.editTextNome.setText(livro.getNome());
        this.editTextAno.setText(Integer.toString(livro.getAno()));
        this.editTextISBN.setText(livro.getISBN());
        this.editTextAutor.setText(livro.getAutor());
        this.editTextCategoria.setText(livro.getCategoria());
    }

    public void updateLivro(View view) {
        Livro up = new Livro();
        up.setNome(editTextNome.getText().toString());
        up.setAno(Integer.parseInt(editTextAno.getText().toString()));
        up.setISBN(ISBNAntigoLivro);
        up.setAutor(editTextAutor.getText().toString());
        up.setCategoria(editTextCategoria.getText().toString());

        serverRequest.post(ServerRequest.UPDATE_LIVRO, up);
    }
}
