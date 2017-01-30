package com.deolino.neto.bibliotecalivre.activies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.deolino.neto.bibliotecalivre.R;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.deolino.neto.bibliotecalivre.model.User;
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;

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

        this.pbLogin = (ProgressBar) findViewById(R.id.pbLogin);
        this.editTextEmail = (EditText) findViewById(R.id.etMail);
        this.editTextSenha = (EditText) findViewById(R.id.etPassword);
        this.pbLogin.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(Response response, String requestUrl) {

    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }

    public void loginButtonClicked(View view) {
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
}
