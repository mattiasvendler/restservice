package com.vendler.resetservice;

/**
 * Created by mattias on 2016-12-01.
 */
public class SampleResponse {
    private boolean ok;

    public SampleResponse() {
    }

    public SampleResponse(boolean ok) {
        this.ok = ok;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
