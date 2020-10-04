package com.orobator.kotlin.intro.lesson25;

import org.jetbrains.annotations.NotNull;

public class FooBar {
    private int bar = 0;

    public void getBar() {
        System.out.println("Called getter!");
        return;
    }

    public @NotNull
    String makeString() {
        return null;
    }

    public String returnPlatformType() {
        return "";
    }

    public void doSomething() {
        KotlinClass kc = new KotlinClass();
//        kc.getFoo();
//        kc.setFoo(null);
    }
}
