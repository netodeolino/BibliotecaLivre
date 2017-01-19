package com.deolino.neto.bibliotecalivre.activies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.adapters.BibliotecaAdapter;
import com.deolino.neto.bibliotecalivre.constants.Constants;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.model.Biblioteca;
import com.deolino.neto.bibliotecalivre.model.Cidade;
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

/**
 * Created by neto on 16/01/17.
 */

public class CidadeDescriptionActivity extends AppCompatActivity implements ServerResponseListener {

    private TextView textViewNome;
    private TextView textViewEstado;

    private ImageView imageViewCidade;

    private ListView listViewBibliotecas;

    private ArrayList<Biblioteca> bibliotecas = new ArrayList<Biblioteca>();

    private ServerRequest serverRequest;

    private Context context;

    private String bibliotecaName;

    private int cidadeCodigo;

    private Intent intent;

    private Cidade cidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_cidade);

        this.imageViewCidade = (ImageView) findViewById(R.id.imageCidade);
        this.textViewNome = (TextView) findViewById(R.id.tvDescriptionName);
        this.textViewEstado = (TextView) findViewById(R.id.tvDescriptionEstado);
        this.listViewBibliotecas = (ListView) findViewById(R.id.lvBibliotecas);

        this.serverRequest = new ServerRequest(this, this);
        this.context = this;
        this.cidade = new Cidade();

        // From MainActivity
        this.intent = getIntent();
        this.cidadeCodigo = intent.getIntExtra("cidadeCodigo", 0);

        serverRequest.get(ServerRequest.FIND_CIDADE_BY_CODIGO, cidadeCodigo);

        /*this.listViewBibliotecas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(Constants.LOG_TEST, "Item " + position + " clicked");

                bibliotecaName = bibliotecas.get(position).getNome();
                Intent intent = new Intent(context, BibliotecaDescriptionActivity.class);
                intent.putExtra("bibliotecaName", bibliotecaName);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_description_cidade, menu);
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

    @Override
    public void onSuccess(Response response, String requestUrl) {
        if (requestUrl.equals(ServerRequest.FIND_CIDADE_BY_CODIGO)) {
            if (response.getResult()) {
                ArrayList<LinkedTreeMap<String, Object>> data = (ArrayList<LinkedTreeMap<String, Object>>) response.getData();
                try {
                    for (LinkedTreeMap<String, Object> mp : data) {
                        Cidade c = new Cidade();

                        c.setNome(mp.get("nome").toString());
                        c.setCodigo((int) Double.parseDouble(mp.get("codigo").toString()));
                        c.setSiglaEstado(mp.get("estado").toString());

                        this.cidade = c;
                    }
                    updateActivityDescription();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(Constants.LOG_TAG, "LOL--> " + e.toString());
                }
            } else {
                Toast.makeText(this, "Não foi possível realizar a operação!", Toast.LENGTH_LONG).show();
            }
        } else if (requestUrl.equals(ServerRequest.FIND_BIBLIOTECA_BY_CIDADE)) {
            if (response.getResult()) {
                ArrayList<LinkedTreeMap<String, Object>> data = (ArrayList<LinkedTreeMap<String, Object>>) response.getData();
                try {
                    for (LinkedTreeMap<String, Object> mp : data) {
                        Biblioteca b = new Biblioteca();

                        b.setNome(mp.get("nome").toString());
                        b.setEndereco(mp.get("endereco").toString());
                        b.setCidade(mp.get("cidade").toString());

                        this.bibliotecas.add(b);
                    }
                    populateListBiblioteca();
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

    private void updateActivityDescription() {
        // AJEITAR A IMAGEM, ESTÁ UMA FIXA
        this.imageViewCidade.setImageResource(R.drawable.cidade_icon);
        this.textViewNome.setText(cidade.getNome());
        this.textViewEstado.setText(cidade.getSiglaEstado());

        serverRequest.get(ServerRequest.FIND_BIBLIOTECA_BY_CIDADE, cidade.getNome());
    }

    private void populateListBiblioteca() {
        Log.d(Constants.LOG_TEST, this.bibliotecas.size()+"");

        BibliotecaAdapter bibliotecaAdapter = new BibliotecaAdapter(getApplicationContext(), this.bibliotecas);
        listViewBibliotecas.setAdapter(bibliotecaAdapter);
    }
}
