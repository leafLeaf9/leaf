package com.gousade.art.concurrent.book.chapter03;

/**
 * @author woxigousade
 * @date 2021/7/2
 * ��ʼ��һ���࣬����ִ�������ľ�̬��ʼ���ͳ�ʼ����������������ľ�̬�ֶΡ���
 * ��Java���Թ淶�����״η�����������һ�����ʱ��һ�����ӿ�����T����������ʼ����
 * 1��T��һ���࣬����һ��T���͵�ʵ����������
 * 2��T��һ���࣬��T��������һ����̬���������á�
 * 3��T��������һ����̬�ֶα���ֵ��
 * 4��T��������һ����̬�ֶα�ʹ�ã���������ֶβ���һ�������ֶΡ�
 * 5��T��һ�������ࣨTop Level Class����Java���Թ淶�ġ�7.6��������һ���������Ƕ����T�ڲ���ִ�С�
 * ����� {@link UnsafeInstanceFactory}��д������������3�����ֱ�ӱ���ʼ���������ӳټ���
 * {@link InstanceFactory}ʹ���ڲ��࣬��{@link InstanceFactory#getInstance()}�е��þ�̬����ֶ���������4����������ų�ʼ��
 */
public class UnsafeInstanceFactory {

    private static Instance instance = new Instance();//���ｫ�����౻��ʼ��

    public static Instance getInstance() {
        return instance;
    }

    static class Instance {
    }
}
