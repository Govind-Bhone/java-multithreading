package example.multithreading.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by govind.bhone on 4/17/2017.
 */

class TreeNode {

    TreeNode parent   = null;
    List<TreeNode> children = new ArrayList<TreeNode>();

    public  void addChild(TreeNode child){
        if(!this.children.contains(child)) {
            synchronized (this){
                this.children.add(child);
            }

            System.out.println("added child using addChild");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            child.setParentOnly(this);
        }
    }

    public synchronized void addChildOnly(TreeNode child){
        if(!this.children.contains(child)){
            System.out.println("set child using addChildOnly");
            this.children.add(child);
        }
    }

    public  void setParent(TreeNode parent){
        synchronized (this){
            this.parent = parent;
        }
        System.out.println("set parent using setParent");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parent.addChildOnly(this);
    }

    public synchronized void setParentOnly(TreeNode parent){
        System.out.println("set parent using setParentOnly");
        this.parent = parent;
    }
}

public class DeadLockExampleInJava2 {

    public static void main(String args[]){
        TreeNode nodes = new TreeNode();

        Thread t1 =new Thread(){
            @Override
            public void run(){
                nodes.addChild(new TreeNode());
            }
        };

        t1.start();

        Thread t2 =new Thread(){
            @Override
            public void run(){
                nodes.children.get(0).setParent(nodes);
            }
        };

        t2.start();




    }

}
