package com.adcb.grpc_server.app;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.ByteBuffer;


public class RpcCodec {
    private static final ObjectMapper M = new ObjectMapper();

    public static byte[] encodeRequest(RpcRequest req) throws Exception {
        byte[] json = M.writeValueAsBytes(req);
        return wrapWithLength(json);
    }

    public static RpcRequest decodeRequest(byte[] bytes) throws Exception {
        byte[] payload = stripLength(bytes);
        return M.readValue(payload, RpcRequest.class);
    }

    public static byte[] encodeResponse(RpcResponse resp) throws Exception {
        byte[] json = M.writeValueAsBytes(resp);
        return wrapWithLength(json);
    }

    public static RpcResponse decodeResponse(byte[] bytes) throws Exception {
        byte[] payload = stripLength(bytes);
        return M.readValue(payload, RpcResponse.class);
    }

    private static byte[] wrapWithLength(byte[] data) {
        ByteBuffer buf = ByteBuffer.allocate(4 + data.length);
        buf.putInt(data.length);
        buf.put(data);
        return buf.array();
    }

    private static byte[] stripWithLengthPrefix(byte[] data) {
        return stripLength(data);
    }

    private static byte[] stripLength(byte[] data) {
        ByteBuffer buf = ByteBuffer.wrap(data);
        int len = buf.getInt();
        byte[] payload = new byte[len];
        buf.get(payload);
        return payload;
    }
}