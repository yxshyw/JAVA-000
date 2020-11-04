package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.Random;

public class HttpEndpointRouterImpl implements HttpEndpointRouter {
    @Override
    public String route(List<String> endpoints) {
        Random r = new Random();
        return endpoints.get(r.nextInt(endpoints.size()));
    }
}
