package com.deolino.neto.bibliotecalivre.activies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CheckableImageButton;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.adapters.CidadeAdapter;
import com.deolino.neto.bibliotecalivre.constants.Constants;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.model.Cidade;
import com.deolino.neto.bibliotecalivre.model.Livro;
import com.deolino.neto.bibliotecalivre.model.User;
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ServerResponseListener, AdapterView.OnItemSelectedListener {

    //private User user;
    private Livro livrao;

    private ArrayList<Cidade> cidades = new ArrayList<Cidade>();
    private ArrayList<String> cidadesString = new ArrayList<String>();

    private ServerRequest request;

    private Spinner spinner1;

    ListView cidadesLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.cidadesLista = (ListView) findViewById(R.id.lvCidades);

        this.request = new ServerRequest(this, this);
        //this.user = new User();
        //this.user.setId("1"); //NÃO TENHO LOGIN e isso não está sendo utilizado também no momento

        this.spinner1 = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.states_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

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
        if (id == R.id.action_create_livro) {
            Intent intent = new Intent(this, CreateLivroActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_description_livro) {
            /* Test */
            // Intent intent = new Intent(this, LivroDescriptionActivity.class);
            // startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getTeste(View view) {
        //request.get(ServerRequest.FIND_LIVRO_BY_NAME, "celular"); // para teste
        //request.get(ServerRequest.ALL_LIVROS, null); // para teste
        //request.get(ServerRequest.REMOVE_LIVRO_BY_NAME, "limoeiro"); // para teste
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
            } else {
                Toast.makeText(this, "Não foi possível realizar a operação!", Toast.LENGTH_LONG).show();
            }
        } else if (requestUrl.equals(ServerRequest.ALL_CIDADES_BY_ESTADO)) {
            if (this.cidades.size() > 0) {
                this.cidades.clear();
            }
            if (response.getResult()) {
                ArrayList<LinkedTreeMap<String, Object>> data = (ArrayList<LinkedTreeMap<String, Object>>) response.getData();
                try {
                    for (LinkedTreeMap<String, Object> mp : data) {
                        Cidade c = new Cidade();

                        c.setNome(mp.get("nome").toString());
                        c.setEstado(mp.get("estado").toString());
                        c.setCodigo((int) Double.parseDouble(mp.get("codigo").toString()));

                        this.cidades.add(c);
                        this.cidadesString.add(c.getNome());
                    }
                    populateListCidade();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(Constants.LOG_TAG, "LOL--> " + e.toString());
                }
            }
        } else {
            Log.d(Constants.LOG_TEST, "REQUEST NÃO TRATADO AINDA :)");
        }
    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        if (!item.equals("")) {
            request.get(ServerRequest.ALL_CIDADES_BY_ESTADO, item);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void populateListCidade() {
        Log.d(Constants.LOG_TEST, this.cidades.size()+"");

        CidadeAdapter cidadeAdapter = new CidadeAdapter(getApplicationContext(), this.cidades);
        cidadesLista.setAdapter(cidadeAdapter);
    }
}
