package com.deolino.neto.bibliotecalivre.server;

import android.util.Log;

import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

/**
 * Created by neto on 06/01/17.
 */

public class ResponseListener implements OptimusHTTP.ResponseListener {

    private Gson gson;
    private ServerResponseListener listener;
    private String requestCode;

    public ResponseListener(ServerResponseListener listener, String requestCode) {
        this.gson = new Gson();
        this.listener = listener;
        this.requestCode = requestCode;
    }


    @Override
    public void onSuccess(String s) {
        Log.i("LOG", s);
        Response res;
        try {
            JsonReader reader = new JsonReader(new StringReader(s));
            reader.setLenient(true);
            res = gson.fromJson(reader, Response.class);
        }catch (Exception e){
            Log.e("LOG", e.toString());
            res = new Response();
            res.setResult(false);
        }
        listener.onSuccess(res, this.requestCode);
    }

    @Override
    public void onFailure(String s) {
        Log.i("LOG", s);
        Response res;
        try{
            JsonReader reader = new JsonReader(new StringReader(s));
            reader.setLenient(true);
            res = gson.fromJson(reader, Response.class);
        }catch (Exception e){
            Log.e("LOG", e.toString());
            res = new Response();
            res.setResult(false);
        }
        listener.onSuccess(res, this.requestCode);
    }
}
