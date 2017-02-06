package com.deolino.neto.bibliotecalivre.activies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.constants.Constants;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.model.User;
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

/**
 * Created by neto on 30/01/17.
 */

public class CreateAccountActivity extends AppCompatActivity implements ServerResponseListener {

    private EditText editTextNome, editTextMail, editTextSenha, editTextRepeatSenha;
    private ServerRequest request;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        this.request = new ServerRequest(this, this);
        this.user = new User();

        this.editTextNome = (EditText) findViewById(R.id.etCreateName);
        this.editTextMail = (EditText) findViewById(R.id.etCreateMail);
        this.editTextSenha = (EditText) findViewById(R.id.etCreatePassword);
        this.editTextRepeatSenha = (EditText) findViewById(R.id.etRepeatPassword);
    }

    @Override
    public void onSuccess(Response response, String requestUrl) {
        if (requestUrl.equals(ServerRequest.SAVE_USER)) {
            if (response.getResult()) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);

                Toast.makeText(this, getString(R.string.registration_completed), Toast.LENGTH_LONG).show();
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

    public void createAccount(View view) {
        String nome = editTextNome.getText().toString();
        String email = editTextMail.getText().toString();
        String senha = editTextSenha.getText().toString();
        String senhaRepeat = editTextRepeatSenha.getText().toString();

        if (!senhaRepeat.equals(senha)) {
            Toast.makeText(CreateAccountActivity.this, getString(R.string.error_password_not_match), Toast.LENGTH_LONG).show();
        } else if (email.isEmpty() || senha.isEmpty() || senhaRepeat.isEmpty() || nome.isEmpty()) {
            Toast.makeText(CreateAccountActivity.this, getString(R.string.error_empty_fields), Toast.LENGTH_LONG).show();
        } else {
            user.setNome(nome);
            user.setEmail(email);
            user.setSenha(senha);

            request.post(ServerRequest.SAVE_USER, user);
        }
    }
}
