package com.yw.yw.config;

public class DataSourceContext {
    public enum DbType {
        ONE,
        TWO
    }

    private static final ThreadLocal<DbType> ctx = new ThreadLocal<>();

    public static void setDbType(DbType dbType) {
        ctx.set(dbType);
    }

    public static DbType getType() {
        return ctx.get() == null ? DbType.ONE : ctx.get();
    }

    public static void remove() {
        ctx.remove();
    }
}
