package com.deolino.neto.bibliotecalivre.activies;

import android.support.v7.app.AppCompatActivity;

import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.server.Response;

/**
 * Created by neto on 30/01/17.
 */

public class CreateAccountActivity extends AppCompatActivity implements ServerResponseListener {
    @Override
    public void onSuccess(Response response, String requestUrl) {

    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }
}
