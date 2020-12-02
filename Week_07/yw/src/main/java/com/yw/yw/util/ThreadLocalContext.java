package com.yw.yw.util;

public class ThreadLocalContext implements AutoCloseable {
    static final ThreadLocal<Object> ctx = new ThreadLocal<>();

    public ThreadLocalContext(Object obj) {
        ctx.set(obj);
    }

    public static Object currentCtx() {
        return ctx.get();
    }

    @Override
    public void close() throws Exception {
        ctx.remove();
    }
}
