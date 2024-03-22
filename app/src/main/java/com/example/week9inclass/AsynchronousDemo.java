package com.example.week9inclass;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynchronousDemo {

    public void runAsyncWrongly(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ArrayList<Double> arr = new ArrayList<>();

        // Execute Runnable in child thread
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                arr.add(3.2);
                System.out.println(arr);
            }
        });

        // Execute in main thread
        System.out.println(arr); // might result in empty list
    }

    public void runAsyncCorrectly(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ArrayList<Double> arr = new ArrayList<>();
        // Execute Runnable in child thread
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                arr.add(3.2);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Execute in main thread
                        System.out.println(arr); // result in a list with 1 item.
                    }
                });
            }
        });


    }
}
