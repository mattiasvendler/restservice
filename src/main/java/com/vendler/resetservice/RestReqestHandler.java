package com.vendler.resetservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by mattias on 2016-11-22.
 */
public class RestReqestHandler extends SimpleChannelInboundHandler<Object> {
    private Map<String, RestContextMapping> resourceMap;
    private String httpMethod;
    private Gson gson = new GsonBuilder().create();
    private RestContextMapping mapping;
    private Object resp;
    public RestReqestHandler(Map<String, RestContextMapping> resourceMap) {
        this.resourceMap=resourceMap;
    }

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest req = (FullHttpRequest) msg;
            httpMethod = req.method().name();

            mapping = resourceMap.get(req.uri());
        }
        Object response = null;
        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            ByteBuf byteBuf = content.content();
            String json = byteBuf.toString(CharsetUtil.UTF_8);

            if(mapping != null && mapping.getMethod(httpMethod)!=null){
                Object o = mapping.getClazz().newInstance();
                Method m = mapping.getMethod(httpMethod);
                if(m.getParameterTypes().length == 1){


                    resp = m.invoke(o,gson.fromJson(json, m.getParameterTypes()[0]));
                    System.out.println(resp);
                }
            }
        }
        if (msg instanceof LastHttpContent) {
            FullHttpResponse httpResponse = new DefaultFullHttpResponse(HTTP_1_1,HttpResponseStatus.OK,Unpooled.copiedBuffer(gson.toJson(resp), CharsetUtil.UTF_8));
            httpResponse.headers().set(CONTENT_TYPE, "text/json; charset=UTF-8");

            ctx.write(httpResponse);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
