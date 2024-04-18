package com.akshay.forkjoinsec9;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {

    private int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {
        if(simulatedWork > 100) {
            System.out.println("splitting the work");
            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork / 2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork / 2);

            action1.fork();
            action2.fork();

            action1.join();
            action2.join();

            /*
            This is equal to above code
             */
            // invokeAll(action1, action2);
        } else {
            System.out.println("working on the task: simulated work size : " + simulatedWork);
        }
    }
}
