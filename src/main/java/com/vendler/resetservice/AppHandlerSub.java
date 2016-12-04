package com.vendler.resetservice;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by mattias on 2016-11-16.
 */
public class AppHandlerSub extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg.toString());
//        ctx.write(msg);
//        ctx.flush();
        ctx.fireChannelRead(msg);
    }
}
