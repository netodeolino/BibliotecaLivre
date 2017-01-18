package com.deolino.neto.bibliotecalivre.activies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
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

public class LivroDescriptionActivity extends AppCompatActivity implements ServerResponseListener {

    private TextView textViewNome;
    private TextView textViewAno;
    private TextView textViewISBN;
    private TextView textViewAutor;
    private TextView textViewCategoria;

    private ImageView imageViewLivro;

    private Livro livro;

    private ServerRequest serverRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_livro);

        this.imageViewLivro = (ImageView) findViewById(R.id.imageLivro);
        this.textViewNome = (TextView) findViewById(R.id.tvDescriptionName);
        this.textViewAno = (TextView) findViewById(R.id.tvDescriptionAno);
        this.textViewISBN = (TextView) findViewById(R.id.tvDescriptionISBN);
        this.textViewAutor = (TextView) findViewById(R.id.tvDescriptionAutor);
        this.textViewCategoria = (TextView) findViewById(R.id.tvDescriptionCategoria);

        this.livro = new Livro();
        this.serverRequest = new ServerRequest(this, this);

        // PARA TEST DE TELA E AFINS DE DESCRIÇAO E ATUALIZAÇAO
        serverRequest.get(ServerRequest.FIND_LIVRO_BY_NAME, "celular"); // para teste
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_description_livro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_update_livro) {
            Intent intent = new Intent(this, UpdateLivroActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
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

                        updateActivityDescription();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(Constants.LOG_TAG, "LOL--> " + e.toString());
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
        Toast.makeText(this, "Não foi possível realizar a operação com sucesso!", Toast.LENGTH_LONG).show();
    }

    private void updateActivityDescription() {
        // AJEITAR A IMAGEM, ESTÁ UMA FIXA
        this.imageViewLivro.setImageResource(R.drawable.capa_livro);
        this.textViewNome.setText(livro.getNome());
        this.textViewAno.setText(Integer.toString(livro.getAno()));
        this.textViewISBN.setText(livro.getISBN());
        this.textViewAutor.setText(livro.getAutor());
        this.textViewCategoria.setText(livro.getCategoria());
    }

}
