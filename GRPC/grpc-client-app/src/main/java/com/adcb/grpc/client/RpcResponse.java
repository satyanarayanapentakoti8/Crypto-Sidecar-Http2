package com.adcb.grpc.client;

import java.util.Map;

public class RpcResponse {
    private Map<String, Object> result;
    private String error;

    public RpcResponse() {}
    public RpcResponse(Map<String, Object> result, String error) {
        this.result = result;
        this.error = error;
    }
    public Map<String, Object> getResult() {
        return result;
    }
    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}
