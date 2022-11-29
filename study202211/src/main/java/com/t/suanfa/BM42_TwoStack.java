package com.t.suanfa;

import java.util.Stack;

public class BM42_TwoStack {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void push(Integer num){
        stack1.push(num);
    }

    public Integer pop(){
        if(stack2.size()<=0){
            while (stack1.size()>0)
                stack2.push(stack1.pop());
        }
        if(stack2.size() > 0)
            return stack2.pop();
        else
            return 9999;
    }

    public static void main(String[] args) {
        BM42_TwoStack demo = new BM42_TwoStack();
        for (int i = 0; i < 5; i++) {
            demo.push(i);
        }

        for (int i = 0; i < 2; i++) {
            System.out.println("---1----" + demo.pop());
        }

        for (int i = 7; i < 10; i++) {
            demo.push(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("---2----" + demo.pop());
        }
    }

}
