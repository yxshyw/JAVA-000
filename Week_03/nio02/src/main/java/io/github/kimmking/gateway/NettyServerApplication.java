package io.github.kimmking.gateway;


import io.github.kimmking.gateway.inbound.HttpInboundServer;

import java.util.ArrayList;
import java.util.List;

public class NettyServerApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) {
        String proxyServer = System.getProperty("proxyServer", "http://localhost:8088");
        String proxyPort = System.getProperty("proxyPort", "8888");
        List<String> endpoints = new ArrayList<>();
        endpoints.add("http://localhost:8088");
        endpoints.add("http://localhost:8089");
        endpoints.add("http://localhost:8087");

        //  http://localhost:8888/api/hello  ==> gateway API
        //  http://localhost:8088/api/hello  ==> backend service

        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
        HttpInboundServer server = new HttpInboundServer(port, proxyServer, endpoints);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:" + port + " for server:" + proxyServer);
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
