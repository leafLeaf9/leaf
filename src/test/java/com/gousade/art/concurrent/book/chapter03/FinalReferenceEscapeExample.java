package com.gousade.art.concurrent.book.chapter03;

public class FinalReferenceEscapeExample {

    static FinalReferenceEscapeExample obj;
    final int i;

    public FinalReferenceEscapeExample() {
        i = 1; //1写final域
        obj = this; //2 this引用在此“逸出”
    }

    public static void writer() {
        new FinalReferenceEscapeExample();
    }

    public static void reader() {
        if (obj != null) { //3
            int temp = obj.i; //4
        }
    }
}
