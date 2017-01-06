package com.deolino.neto.bibliotecalivre.server;

/**
 * Created by neto on 06/01/17.
 */

public class Response {
    private boolean result;
    private Object data;

    public boolean getResult(){
        return result;
    }

    public Object getData(){
        return data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "result=" + result +
                ", data=" + data +
                '}';
    }
}
