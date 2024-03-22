package com.example.week9inclass;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentDemo {
    public static void main (String[] args) {
        int n = 20 ;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        String s = "abcd" ;

        // This for loop is executed in main thread
        for ( int i = 0 ; i < s.length(); i++ ){
            executorService.execute(
                    // The run method in PrintStr is executed in background thread
                    new PrintStr( String.valueOf( s.charAt(i) ) , n));
        }
        executorService.shutdown();
    }
}
class PrintStr implements Runnable {
    String s;
    int times;

    PrintStr(String s, int times) {
        this.s = s;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.print(s + i + " ");
        }
        System.out.println();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
