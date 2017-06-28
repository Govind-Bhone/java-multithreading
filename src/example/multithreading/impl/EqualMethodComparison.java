package example.multithreading.impl;

/**
 * Created by govind.bhone on 6/26/2017.
 */


class Name {
    String name = null;

    @Override
    public boolean equals(Object o) {
        return super.equals(0);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    Name(String name) {
        this.name = name;
    }
}

public class EqualMethodComparison {
    public static void main(String args[]) {
        Name name1 = new Name("govind");
        Name name2 = new Name("ashish");
        Name name3 = new Name("govind");
        System.out.println(name1.equals(name2));
        System.out.println(name1.equals(name3));
        System.out.println(name1 == name2);
        System.out.println(name1 == name3);

    }
}
