package com.t.pattern;

public class Singleton05_1 {

    private static class Holder{
        private static final Singleton05_1 INSTANCE = new Singleton05_1();
    }
    private Singleton05_1(){}

    private static final Singleton05_1 getInstance(){
        return Holder.INSTANCE;
    }

}
