package com.orobator.kotlin.intro.lesson26

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Normal functions can't call suspend functions

// We get into the land of coroutines and suspending functions using
// coroutine builders.

// Launch - One of the most prominent coroutine builders. Fire & forget.
fun main2() {
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}

// Lab 0

// Runs a new coroutine and blocks the current thread interruptibly
// until its completion.
//
// This function should not be used from a coroutine.
//
// It is designed to bridge regular blocking code to libraries that
// are written in suspending style, to be used in main functions and
// in tests.
fun main() = runBlocking { // start main coroutine
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000L)
        println("World!")
    }
    println("Hello,")     // main coroutine continues here immediately
    delay(2000L)  // delaying for 2 seconds to keep JVM alive
}

// break down launch, runBlocking, async?
// Structured concurrency, scope, Job