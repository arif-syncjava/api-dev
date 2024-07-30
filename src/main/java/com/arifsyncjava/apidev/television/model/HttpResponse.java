package com.arifsyncjava.apidev.television.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponse {
    @JsonFormat (
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MMM-yyyy HH:mm:ss"
    )
    private LocalDateTime time;
    private int code;
    private String status;
    private Map<?,?> data;

    public HttpResponse (HttpStatus httpStatus, Map<?,?> data) {
        this.time = LocalDateTime.now();
        this.code = httpStatus.value();
        this.status = httpStatus.getReasonPhrase();
        this.data = data;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getCode() {
        return code;
    }



    public String getStatus() {
        return status;
    }



    public Map<?, ?> getData() {
        return data;
    }


}
