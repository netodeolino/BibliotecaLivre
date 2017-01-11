package com.deolino.neto.bibliotecalivre.activies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.constants.Constants;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.model.Livro;
import com.deolino.neto.bibliotecalivre.model.User;
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ServerResponseListener {

    private User user;
    private Livro livrao;

    private ServerRequest request;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.request = new ServerRequest(this, this);
        this.user = new User();
        this.user.setId("1"); //NÃO TENHO LOGIN e isso não está sendo utilizado também no momento

        listView = ( ListView )findViewById( R.id.listview );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    public void getTeste(View view){
        //request.get(ServerRequest.FIND_LIVRO_BY_NAME, "limoeiro");
        //request.get(ServerRequest.ALL_LIVROS, null);
        Intent intent = new Intent(this, CreateLivroActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSuccess(Response response, String requestUrl) {
        if (requestUrl.equals(ServerRequest.FIND_LIVRO_BY_NAME)) {
            if (response.getResult()) {
                ArrayList<LinkedTreeMap<String, Object>> data = (ArrayList<LinkedTreeMap<String, Object>>) response.getData();
                try {
                    for (LinkedTreeMap<String, Object> mp : data) {
                        Livro livro = new Livro();

                        livro.setNome(mp.get("nome").toString());
                        livro.setAno((int) Double.parseDouble(mp.get("ano").toString()));
                        livro.setISBN(mp.get("ISBN").toString());
                        livro.setAutor(mp.get("autor").toString());
                        livro.setCategoria(mp.get("categoria").toString());

                        this.livrao = livro;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(Constants.LOG_TAG, "LOL--> " + e.toString());
                }
                // SÓ PARA TESTE MESMO
                /*Log.i(Constants.LOG_TEST, livrao.getNome());
                Log.i(Constants.LOG_TEST, livrao.getAutor());
                Log.i(Constants.LOG_TEST, String.valueOf(livrao.getAno()));
                Log.i(Constants.LOG_TEST, livrao.getISBN());*/
            }
        } else {
            Log.d(Constants.LOG_TEST, "NAO FOI UM FIND LIVRO PELO NOME :)");
        }
    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }
}
