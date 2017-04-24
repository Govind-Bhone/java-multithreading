package example.basic.coreJava.programs;

/**
 * Created by govind.bhone on 4/21/2017.
 */

class SuperClass {
    public static void print(){
        System.out.println("Hello SuperClass");
    }

    public void print(String msg){
        System.out.println(msg+"Parent");
    }

    public void println(){System.out.println("Hello\n");}
}


class SubClass extends SuperClass{

    public static void print(){
        System.out.println("Hello SubClass");
    }


    @Override
    public void print(String msg){
        System.out.println(msg+"Derived");
    }


    public void println(){System.out.println("Hello Subclass \n");}
}

public class OverridingStaticMethods {
    public static void main(String args[]){
        SuperClass clzz= new SubClass();
        SubClass.print();
        clzz.print("hello");
        clzz.println();
    }
}
