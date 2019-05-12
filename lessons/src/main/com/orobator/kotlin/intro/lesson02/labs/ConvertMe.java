package com.orobator.kotlin.intro.lesson02.labs;

// Create a class called KonvertMe that is the Kotlin equivalent of ConvertMe.
// Here's a start: class KonvertMe { }
public class ConvertMe {

    public void doStuff(int foo, String bar, double baz) {
        System.out.println("foo: " + foo + ", bar: " + bar + ", baz: " + baz);
    }

    public void doStuff(int foo, String bar) {
        doStuff(foo, bar, 42.0);
    }

    public void doStuff(int foo) {
        doStuff(foo, "BAR");
    }
}
