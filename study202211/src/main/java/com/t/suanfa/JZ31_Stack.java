package com.t.suanfa;

import java.util.Stack;

public class JZ31_Stack {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        int popNum=0;

        for(int i=0;i<pushA.length;i++){
            stack.push(pushA[i]);
            //while 不断对比栈顶元素，检查了 pushA压栈过程中出栈的情况。
            while(!stack.empty()&&stack.peek()==popA[popNum]){
                stack.pop();
                popNum++;
            }
        }
        return stack.empty();//熟练使用Stack的API
    }
}

