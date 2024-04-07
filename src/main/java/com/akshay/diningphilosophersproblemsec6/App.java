package com.akshay.diningphilosophersproblemsec6;

import com.akshay.diningphilosophersproblemsec6.model.Chopstick;
import com.akshay.diningphilosophersproblemsec6.model.Philosopher;
import com.akshay.diningphilosophersproblemsec6.utils.Constants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.akshay.diningphilosophersproblemsec6.utils.Constants.NUMBER_OF_CHOPSTICKS;
import static com.akshay.diningphilosophersproblemsec6.utils.Constants.NUMBER_OF_PHILOSOPHERS;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_PHILOSOPHERS);;
        Philosopher[] philosophers = null;
        Chopstick[] chopsticks = null;

        try {
            philosophers = new Philosopher[NUMBER_OF_PHILOSOPHERS];
            chopsticks = new Chopstick[NUMBER_OF_CHOPSTICKS];

            for (int i = 0; i < NUMBER_OF_CHOPSTICKS; i++) {
                chopsticks[i] = new Chopstick(i);
            }

            for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
                Philosopher philosopher = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % NUMBER_OF_CHOPSTICKS]);
                philosophers[i] = philosopher;
            }


            for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
                executorService.execute(philosophers[i]);
            }

            Thread.sleep(Constants.SIMULATION_TIME);

            for (Philosopher philosopher : philosophers) {
                philosopher.setFull(true);
            }

        } finally {
            executorService.shutdown();

            while (!executorService.isTerminated()) {
                Thread.sleep(1000);
            }

            for (Philosopher philosopher : philosophers) {
                System.out.println("Philosopher -> " + philosopher);
            }
        }
    }
}
