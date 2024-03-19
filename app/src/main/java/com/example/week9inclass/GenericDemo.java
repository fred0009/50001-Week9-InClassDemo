package com.example.week9inclass;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

public class GenericDemo {
    public static void main(String[] args) {
        ArrayList<Pair<Chicken,Double>> arrPair = new ArrayList<>();

        Pair<Chicken, Double> p1 = new Pair<>(new Chicken(1.0), 3.0); // Chicken must be comparable
        Pair<Chicken, Double> p2 = new Pair<>(new Chicken(2.0), 1.0);
        Pair<Chicken, Double> p3 = new Pair<>(new Chicken(2.0), 0.10);

        arrPair.add(p1);
        arrPair.add(p2);
        arrPair.add(p3);

        Collections.sort(arrPair);
        System.out.println(arrPair.toString());

    }
}

class Chicken implements Comparable<Chicken>{
    double height;
    Chicken(double height) {
        this.height = height;
    }

    @Override
    public int compareTo(Chicken chicken) {
        return Double.compare(this.height, chicken.height);
    }

    @NonNull
    @Override
    public String toString() {
        return "Chicken " + this.height;
    }
}

class Pair<T extends Comparable<T>, S extends Comparable<S>> implements Comparable<Pair<T,S>>{
    public T first;
    public S second;
    public Pair(T first, S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Pair<T, S> otherPair) {
        // Context. Comparing Pair = Comparing the first item,
        // If it is the same, then compare the second item.
        int c = this.first.compareTo( otherPair.first );
        if (c==0) {
            return this.second.compareTo(otherPair.second);
        }
        return c;
    }

    @NonNull
    @Override
    public String toString() {
        return first.toString() + " - " + second.toString();
    }
}