package com.deolino.neto.bibliotecalivre.server;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.deolino.neto.bibliotecalivre.constants.Constants;
import com.deolino.neto.bibliotecalivre.interfaces.ServerResponseListener;
import com.google.gson.Gson;

/**
 * Created by neto on 06/01/17.
 */

public class ServerRequest {

    private static final String SERVER = "http://192.168.1.18:3000/";
    public static final String LOGIN = "login";
    public static final String GET_ROLES = "roles";
    public static final String GET_TRAINEE_TEAMS = "team/trainee/";
    public static final String CREATE_ACCOUNT = "trainee";
    public static final String PRESENCE_TRAINEE = "trainee/presence/device";


    private OptimusHTTP client;
    private ServerResponseListener listener;
    private Gson gson;

    public ServerRequest(Context context, ServerResponseListener listener){
        this.client = new OptimusHTTP(context);
        this.client.setConnectTimeout(10000);
        this.listener = listener;
        this.gson = new Gson();
        this.client.enableDebugging();
    }

    public void post(String url, Object param){
        ResponseListener responseListener = new ResponseListener(listener, url);
        try{
            client.setMethod(OptimusHTTP.METHOD_POST);
            String serverUrl = SERVER+url;

            ArrayMap<String, String> params = new ArrayMap<String, String>();

            this.client.setContentType(OptimusHTTP.CONTENT_TYPE_JSON);
            String jsonParam = gson.toJson(param);
            Log.i("LOG", "POST PARAMS: "+jsonParam);
            params.put("", jsonParam);

            Log.i(Constants.LOG_TAG, "Creating a POST request to "+serverUrl);
            client.makeRequest(serverUrl, params, responseListener);

        }catch (Exception ex){
            Log.e(Constants.LOG_TAG, ex.toString());
        }
    }

    public void get(String url, Object param){
        ResponseListener responseListener = new ResponseListener(listener, url);
        try{
            this.client.setMethod(OptimusHTTP.METHOD_GET);
            this.client.setContentType(OptimusHTTP.CONTENT_TYPE_JSON);
            String serverUrl = "";
            if(param != null) {
                serverUrl = SERVER + url + param.toString();
            }else {
                serverUrl = SERVER + url;
            }
            Log.i(Constants.LOG_TAG, "Creating a GET request to "+serverUrl);

            client.makeRequest(serverUrl, new ArrayMap<String, String>(), responseListener);

        }catch (Exception ex){
            Log.i(Constants.LOG_TAG, ex.toString());
        }
    }


}