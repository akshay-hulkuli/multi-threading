package com.akshay.concurrentcollectionssec5;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class MapFirstWorker implements Runnable {

    private ConcurrentMap<String, Integer> map;

    public MapFirstWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            map.put("B", 12);
            Thread.sleep(1000);
            map.put("Z", 14);
            map.put("A", 112);
            map.put("V", 982);
            map.put("D", 16);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class MapSecondWorker implements Runnable {

    private ConcurrentMap<String, Integer> map;

    public MapSecondWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(map.get("A"));
            System.out.println(map.get("B"));
            Thread.sleep(1000);
            System.out.println(map.get("Z"));
            System.out.println(map.get("D"));
            System.out.println(map.get("V"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ConcurrentMapExampleSEC7 {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
        MapFirstWorker firstWorker = new MapFirstWorker(map);
        MapSecondWorker secondWorker = new MapSecondWorker(map);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }
}
