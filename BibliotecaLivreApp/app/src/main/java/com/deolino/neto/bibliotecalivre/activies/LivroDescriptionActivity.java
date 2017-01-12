package com.deolino.neto.bibliotecalivre.activies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.server.Response;

/**
 * Created by neto on 12/01/17.
 */

public class LivroDescriptionActivity extends AppCompatActivity implements ServerResponseListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_livro);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_description, menu);
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

    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }
}
