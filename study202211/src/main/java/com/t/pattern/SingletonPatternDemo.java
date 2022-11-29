package com.t.pattern;

public class SingletonPatternDemo {
    public static void main(String[] args) {

        //不合法的构造函数
        //编译时错误：构造函数 SingleObject() 是不可见的
//        SingleObject object = new SingleObject();

        //获取唯一可用的对象
        Singleton03 object = Singleton03.getInstance();

        //显示消息
        object.showMessage();

        //
        Singleton01 singleton01 = Singleton01.getInstance();
        singleton01.showMessage();

        //
        Singleton02 singleton02 = Singleton02.getInstance();
        singleton02.showMessage();

        Singleton06.INSTANCE.showMsg();
    }
}
