package io.github.kimmking.gateway.outbound.netty4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

public class NettyHttpClient {
    public NettyHttpClient() {

    }

    public void connect(String host, int port, String uri, ChannelHandlerContext ctx) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
//                    ch.pipeline().addLast(new HttpResponseDecoder());
////                     客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
//                    ch.pipeline().addLast(new HttpRequestEncoder());
//                    ch.pipeline().addLast(new NettyHttpClientOutboundHandler());
                    ch.pipeline().addLast(new HttpClientCodec());
                    ch.pipeline().addLast(new HttpObjectAggregator(1024 * 1024));
                    ch.pipeline().addLast(new NettyHttpClientOutboundHandler(uri, ctx));
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();


//            f.channel().write("request");
//            f.channel().flush();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

//    public static void main(String[] args) throws Exception {
//        NettyHttpClient client = new NettyHttpClient();
//        client.connect("127.0.0.1", 8088);
//    }
}