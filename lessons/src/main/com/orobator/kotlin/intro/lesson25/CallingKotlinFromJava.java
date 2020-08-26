package com.orobator.kotlin.intro.lesson25;

public class CallingKotlinFromJava {
    /////////////
    // Properties
    public void interactWithKotlin() {
        KotlinClass kotlinClass = new KotlinClass();
        kotlinClass.jvmFoo();
    }
    
}
