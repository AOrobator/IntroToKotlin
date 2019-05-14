package com.orobator.kotlin.intro.lesson26

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Normal functions can't call suspend functions

// We get into the land of coroutines and suspending functions using
// coroutine builders.

// Launch - One of the most prominent coroutine builders. Fire & forget.
fun main() {
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}

// Lab 0

// break down launch, runBlocking, async?
// Structured concurrency, scope, Job