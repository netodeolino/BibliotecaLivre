package com.deolino.neto.bibliotecalivre.activies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.deolino.neto.bibliotecalivre.R;
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

    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }

    public void resetPassword(View view) {
        String email = editTextMail.getText().toString();

        if (email.isEmpty()) {
            Toast.makeText(ResetPasswordActivity.this, getString(R.string.error_empty_fields), Toast.LENGTH_LONG).show();
        } else {

        }
    }
}
