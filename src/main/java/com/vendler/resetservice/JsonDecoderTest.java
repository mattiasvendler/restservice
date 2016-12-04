package com.vendler.resetservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * Created by mattias on 2016-11-21.
 */
public class JsonDecoderTest extends JsonObjectDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        super.decode(ctx, in, out);
        for(Object o : out){
            ByteBuf b = (ByteBuf)o;
            System.out.println( b.toString(CharsetUtil.UTF_8));
            Gson gson = new GsonBuilder().create();
            JsonArray pojo = gson.fromJson(b.toString(CharsetUtil.UTF_8),JsonArray.class);
            for(JsonElement e : pojo) {
                System.out.println(gson.fromJson(e, SamplePOJO.class));
            }
        }

    }

}
