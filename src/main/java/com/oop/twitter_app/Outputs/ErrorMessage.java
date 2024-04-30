package com.oop.twitter_app.Outputs;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorMessage {
    public String Error;

    public ErrorMessage(String Error) {
        this.Error = Error;
    }

    @JsonIgnore
    public String getError() {
        return Error;
    }
    @JsonIgnore
    public void setError(String error) {
        this.Error = error;
    }

}




