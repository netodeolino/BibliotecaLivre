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
import com.deolino.neto.bibliotecalivre.server.Response;
import com.deolino.neto.bibliotecalivre.server.ServerRequest;

/**
 * Created by neto on 30/01/17.
 */

public class ResetPasswordActivity extends AppCompatActivity implements ServerResponseListener {

    private EditText editTextMail;
    private ServerRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        this.request = new ServerRequest(this, this);

        this.editTextMail = (EditText) findViewById(R.id.etResetPassword);
    }

    @Override
    public void onSuccess(Response response, String requestUrl) {
        if (requestUrl.equals(ServerRequest.RESET_PASSWORD)) {
            if (response.getResult()) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);

                Toast.makeText(this, getString(R.string.reset_password_ok), Toast.LENGTH_LONG).show();
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

    public void resetPassword(View view) {
        String email = editTextMail.getText().toString();

        if (email.isEmpty()) {
            Toast.makeText(ResetPasswordActivity.this, getString(R.string.error_empty_fields), Toast.LENGTH_LONG).show();
        } else {
            request.get(ServerRequest.RESET_PASSWORD, email);
        }
    }
}
