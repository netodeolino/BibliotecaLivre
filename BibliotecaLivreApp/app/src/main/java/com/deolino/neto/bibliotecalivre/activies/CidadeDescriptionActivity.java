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

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.constants.Constants;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.model.Biblioteca;
import com.deolino.neto.bibliotecalivre.model.Cidade;
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;

import java.util.ArrayList;

/**
 * Created by neto on 16/01/17.
 */

public class CidadeDescriptionActivity extends AppCompatActivity implements ServerResponseListener {

    private TextView textViewNome;
    private TextView textViewEstado;

    private ImageView imageViewCidade;

    private ListView listViewBibliotecas;

    ArrayList<Biblioteca> bibliotecas = new ArrayList<Biblioteca>();

    private ServerRequest serverRequest;

    private Context context;

    private String bibliotecaName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_cidade);

        this.imageViewCidade = (ImageView) findViewById(R.id.ivCidadeImage);
        this.textViewNome = (TextView) findViewById(R.id.tvDescriptionName);
        this.textViewEstado = (TextView) findViewById(R.id.tvDescriptionEstado);
        this.listViewBibliotecas = (ListView) findViewById(R.id.lvBibliotecas);

        this.serverRequest = new ServerRequest(this, this);
        this.context = this;

        // CARREGAR O QUE FOI PASSADO NA TELA ANTERIOR

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

    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }
}
