package com.gousade.art.concurrent.book.chapter03;

public class InstanceFactory {
    public static Instance getInstance() {
        return InstanceHolder.instance; //这里将导致InstanceHolder类被初始化
    }

    private static class InstanceHolder {
        public static Instance instance = new Instance();
    }

    static class Instance {
    }
}
