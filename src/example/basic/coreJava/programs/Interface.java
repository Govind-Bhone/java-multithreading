package example.basic.coreJava.programs;

/**
 * Created by govind.bhone on 4/21/2017.
 */

interface A {
    int a = 10;
    String b = "hello";

    public void print();
}


class B implements A {
    public void print() {
        System.out.println(a);
    }
}

interface D extends A{

}

class C implements D {
    public void print() {
        System.out.println(b);
    }
}


public class Interface {
    public static void main(String args[]) {
        B b = new B();
        b.print();
        C c = new C();
        c.print();

        A a = new A() {
            @Override
            public void print() {
                System.out.println("Hello");
            }
        };
        a.print();

        A d = new B();
        d.print();
    }
}