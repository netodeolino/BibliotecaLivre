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

    private static final String SERVER = "http://192.168.0.112:3000/";

    public static final String LOGIN = "login";

    /* LIVRO */
    public static final String FIND_LIVRO_BY_NAME = "findlivrobyname/";
    public static final String FIND_LIVRO_BY_ISBN = "findlivrobyisbn/";
    public static final String ALL_LIVROS = "findalllivros/";
    public static final String SAVE_LIVRO = "savelivro";
    public static final String REMOVE_LIVRO_BY_NAME = "removelivrobyname/";
    public static final String UPDATE_LIVRO = "updatelivrobyisbn/";

    /* CIDADE */
    public static final String FIND_CIDADE_BY_CODIGO = "findcidadebycodigo/";
    public static final String ALL_CIDADES_BY_ESTADO = "findallcidadesbyestado/";

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