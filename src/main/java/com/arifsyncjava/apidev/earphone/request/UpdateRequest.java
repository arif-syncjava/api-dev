package com.arifsyncjava.apidev.earphone.request;

public class UpdateRequest  {
    private Long id;
    private UpdateRequestBody requestBody;

    public UpdateRequest(Long id, UpdateRequestBody requestBody) {
        this.id = id;
        this.requestBody = requestBody;
    }

    public Long getId() {
        return id;
    }

    public UpdateRequestBody getRequestBody() {
        return requestBody;
    }
}
