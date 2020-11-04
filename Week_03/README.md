## 使用netty实现http的client

1.NettyHttpClient.java
```java
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
                    ch.pipeline().addLast(new HttpClientCodec());
                    ch.pipeline().addLast(new HttpObjectAggregator(1024 * 1024));
                    ch.pipeline().addLast(new NettyHttpClientOutboundHandler(uri, ctx));
                }
            });
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
 ```
2.NettyHttpClientOutboundHandler.java
```java
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
 ```

## 实现request的filter

```java
public class HttpRequestFilterImpl implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("nio", "yw");
    }
}
```

## 实现随机路由

```java
public class HttpEndpointRouterImpl implements HttpEndpointRouter {
    @Override
    public String route(List<String> endpoints) {
        Random r = new Random();
        return endpoints.get(r.nextInt(endpoints.size()));
    }
}
```
