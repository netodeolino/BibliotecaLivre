package com.deolino.neto.bibliotecalivre.activies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.server.Response;

/**
 * Created by neto on 29/01/17.
 */

public class LoginActivity extends AppCompatActivity implements ServerResponseListener {

    private ProgressBar pbLogin;
    private EditText editTextEmail, editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.pbLogin = (ProgressBar) findViewById(R.id.pbLogin);
        this.editTextEmail = (EditText) findViewById(R.id.etMail);
        this.editTextSenha = (EditText) findViewById(R.id.etPassword);
    }

    @Override
    public void onSuccess(Response response, String requestUrl) {

    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }
}
