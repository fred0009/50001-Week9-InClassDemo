package com.example.week9inclass;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImmutableDemo {
    // This must be demonstrated while running the emulator, otherwise cannot
    // access the main looper of android system.
    public void doSomething() {
        int s = 0;

        // Using container to contain data to be shared to child thread
        final Container<Integer> cs = new Container<>(s);
        final ArrayList<Integer> arr = new ArrayList<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        // Instantiate handler and reference to main looper
        final Handler handler = new Handler(Looper.getMainLooper());

        executor.execute( new Runnable() {
            @Override
            public void run () {
                // executed in a worker thread
                int s1 = cs.get() + 1;
                cs.set(s1);
                arr.add(s1);

                handler.post(new Runnable() { // remember that handler has reference to main looper already
                    // executed in main thread
                    @Override
                    public void run() {
                        System.out.println(arr); // guaranteed to print the array after it is appended
                    }
                });
            }
        });

//        System.out.println(arr); // The array printed here may be before the child thread finish adding an element

    }
}

class Container<T>{
    T value;
    Container(T v) { this.value = v; }
    void set(T v) { this.value = v; }
    T get() { return this.value; }
}