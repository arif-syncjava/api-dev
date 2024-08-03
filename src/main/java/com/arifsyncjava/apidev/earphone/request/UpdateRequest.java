package com.arifsyncjava.apidev.earphone.request;

public class UpdateRequest  {
    private String id;
    private UpdateRequestBody requestBody;

    public UpdateRequest(String id, UpdateRequestBody requestBody) {
        this.id = id;
        this.requestBody = requestBody;
    }

    public String getId() {
        return id;
    }

    public UpdateRequestBody getRequestBody() {
        return requestBody;
    }
}
