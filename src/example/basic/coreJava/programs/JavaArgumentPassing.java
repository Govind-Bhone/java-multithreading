package example.basic.coreJava.programs;

/**
 * Created by govind.bhone on 4/21/2017.
 */

class Argument {
    int b ;
    Argument(int b){
        this.b=b;
    }

    public void setB(int b){
        this.b=b;
    }

    /* Here for primitive case ,`a` is formal parameter and require seperate space on execution stack to store the value , it is
     * not actual value we passed  */
    public void update (int a){
        a=a+1;
        System.out.println("Formal parameter value"+a);
    }

    public void update1(Argument a ){
        Argument b=new Argument(20);
        a=b;
        System.out.println("Formal Argument value "+a);
    }

    public String toString(){
        return String.valueOf(b);
    }
}

public class JavaArgumentPassing {
    public static void main(String args[]){
        //Below statement is like
        // Argument *arg ;
        // both reference and primitives are passed as call by value
        Argument arg= new Argument(10);
        int a =10;
        arg.update(a);
        System.out.println("Actual parameter value"+a);
        arg.update1(arg);
        System.out.println("Actual Argument value "+arg);


    }
}
