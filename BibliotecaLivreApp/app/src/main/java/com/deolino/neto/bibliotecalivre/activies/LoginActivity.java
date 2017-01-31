package com.deolino.neto.bibliotecalivre.activies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
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
 * Created by neto on 29/01/17.
 */

public class LoginActivity extends AppCompatActivity implements ServerResponseListener {

    private ProgressBar pbLogin;
    private EditText editTextEmail, editTextSenha;
    private User user;
    private ServerRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String pass = prefs.getString("user_pass", "null");

        if (pass.equals("null")) {
            this.pbLogin = (ProgressBar) findViewById(R.id.pbLogin);
            this.editTextEmail = (EditText) findViewById(R.id.etMail);
            this.editTextSenha = (EditText) findViewById(R.id.etPassword);
            this.pbLogin.setVisibility(View.GONE);
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    @Override
    public void onSuccess(Response response, String requestUrl) {
        if (requestUrl.equals(ServerRequest.LOGIN_USER)) {
            if (response.getResult()) {
                ArrayList<LinkedTreeMap<String, Object>> data = (ArrayList<LinkedTreeMap<String, Object>>) response.getData();
                try {
                    for (LinkedTreeMap<String, Object> mp : data) {
                        User u = new User();

                        u.setNome(mp.get("nome").toString());
                        u.setEmail(mp.get("email").toString());
                        u.setSenha(mp.get("senha").toString());

                        user = u;
                    }
                    login();
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

    }

    private void loginButtonClicked(View view) {
        pbLogin.setVisibility(View.VISIBLE);

        String mail = editTextEmail.getText().toString();
        String password = editTextSenha.getText().toString();

        User temp = new User();
        temp.setEmail(mail);
        temp.setSenha(password);

        if (mail.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, getString(R.string.error_empty_fields), Toast.LENGTH_LONG).show();
        } else {
            request.post(ServerRequest.LOGIN_USER, temp);
        }
    }

    private void login() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("user_pass", user.getSenha());
        editor.apply();

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
