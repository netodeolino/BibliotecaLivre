package com.deolino.neto.bibliotecalivre.interfaces;

import com.deolino.neto.bibliotecalivre.server.Response;

/**
 * Created by neto on 06/01/17.
 */

public interface ServerResponseListener {
    void onSuccess(Response response, String requestUrl);
    void onFailure(Response response, String requestUrl);
}
