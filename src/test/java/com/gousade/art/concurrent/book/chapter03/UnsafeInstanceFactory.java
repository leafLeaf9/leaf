package com.gousade.art.concurrent.book.chapter03;

/**
 * @author woxigousade
 * @date 2021/7/2
 * 初始化一个类，包括执行这个类的静态初始化和初始化在这个类中声明的静态字段。根
 * 据Java语言规范，在首次发生下列任意一种情况时，一个类或接口类型T将被立即初始化。
 * 1）T是一个类，而且一个T类型的实例被创建。
 * 2）T是一个类，且T中声明的一个静态方法被调用。
 * 3）T中声明的一个静态字段被赋值。
 * 4）T中声明的一个静态字段被使用，而且这个字段不是一个常量字段。
 * 5）T是一个顶级类（Top Level Class，见Java语言规范的§7.6），而且一个断言语句嵌套在T内部被执行。
 * 如果是 {@link UnsafeInstanceFactory}的写法，满足条件3，类会直接被初始化，则不是延迟加载
 * {@link InstanceFactory}使用内部类，在{@link InstanceFactory#getInstance()}中调用静态类的字段满足条件4，类在这里才初始化
 */
public class UnsafeInstanceFactory {

    private static Instance instance = new Instance();//这里将导致类被初始化

    public static Instance getInstance() {
        return instance;
    }

    static class Instance {
    }
}
