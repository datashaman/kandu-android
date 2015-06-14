package com.inomma.kandu.server;

public abstract class ResponseHandler<T extends Response> {

    public abstract void handleResponse(T response);
}
