package com.akshay.virtualthreadsec11;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.StructuredTaskScope;

public class StructuredAndUnstructuredConcurrencySEC5 {
    public static void main(String[] args) {

        // here virtual threads are created
//        try (var scope = new StructuredTaskScope<String>()) {
//            var process1 = new LongProcess(3, "result1");
//            var process2 = new LongProcess(7, "result2");
//
//            StructuredTaskScope.Subtask<String> res1 = scope.fork(process1);
//            StructuredTaskScope.Subtask<String> res2 = scope.fork(process2);
//            scope.join();
//
//            if (res1.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
//                System.out.println(res1.get());
//            }
//            if (res2.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
//                System.out.println(res2.get());
//            }
//            System.out.println(res1.get() + res2.get());
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }


//        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
//            var process1 = new LongProcessFail(1, "result1", true);
//            var process2 = new LongProcessFail(7, "result2", false);
//
//            StructuredTaskScope.Subtask<String> res1 = scope.fork(process1);
//            StructuredTaskScope.Subtask<String> res2 = scope.fork(process2);
//            scope.join();
//
//            try {
//                scope.throwIfFailed();
//                if (res1.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
//                    System.out.println(res1.get());
//                }
//                if (res2.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
//                    System.out.println(res2.get());
//                }
//                System.out.println(res1.get() + res2.get());
//            } catch (Exception e) {
//                System.out.println("Terminating all the threads");
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            var process1 = new LongProcessFail(1, "result1", true);
            var process2 = new LongProcessFail(7, "result2", true);

            StructuredTaskScope.Subtask<String> res1 = scope.fork(process1);
            StructuredTaskScope.Subtask<String> res2 = scope.fork(process2);
            scope.join();

            try {
                String result = scope.result();
                System.out.println(result);
            } catch (Exception e) {
                System.out.println("No task is successful. No solutions available");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class LongProcess implements Callable<String> {

    private int timeToSleep;
    private String result;

    public LongProcess(int timeToSleep, String result) {
        this.timeToSleep = timeToSleep;
        this.result = result;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(Duration.ofSeconds(timeToSleep));
        return result;
    }
}

class LongProcessFail implements Callable<String> {

    private int timeToSleep;
    private String result;
    private boolean fail;

    public LongProcessFail(int timeToSleep, String result, Boolean fail) {
        this.timeToSleep = timeToSleep;
        this.result = result;
        this.fail = fail;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Starting the thread");
        Thread.sleep(Duration.ofSeconds(timeToSleep));
        if (fail) {
            System.out.println("Execution failed");
            throw new Exception();
        }
        return result;
    }
}
