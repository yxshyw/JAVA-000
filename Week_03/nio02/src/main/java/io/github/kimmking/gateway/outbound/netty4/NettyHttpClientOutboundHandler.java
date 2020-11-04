package io.github.kimmking.gateway.outbound.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleUserEventChannelHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.lang.ref.ReferenceQueue;
import java.net.URI;
import java.net.URISyntaxException;

public class NettyHttpClientOutboundHandler extends ChannelInboundHandlerAdapter {
    private String uri;
    private ChannelHandlerContext ctx;

    public NettyHttpClientOutboundHandler(String uri, ChannelHandlerContext ctx) {
        this.uri = uri;
        this.ctx = ctx;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        URI uri = new URI(this.uri);
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toASCIIString());
        request.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        request.headers().add(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        ctx.writeAndFlush(request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(msg);
        if (msg instanceof FullHttpResponse) {
            FullHttpResponse res = (FullHttpResponse) msg;
            System.out.println(res.content().toString(CharsetUtil.UTF_8));
            ByteBuf buf = ((FullHttpResponse) msg).content();
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(res.headers().get("Content-Length")));
            System.out.println(response);
            this.ctx.writeAndFlush(response);
        }
    }
}