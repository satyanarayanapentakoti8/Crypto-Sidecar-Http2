package com.adcb.grpc_server.app;

import java.util.Map;

public class RpcRequest {
    private String method;
    private Map<String, Object> params;

    public RpcRequest() {}
    public RpcRequest(String method, Map<String, Object> params) {
        this.method = method;
        this.params = params;
    }

    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, Object> getParams() {
        return params;
    }
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}