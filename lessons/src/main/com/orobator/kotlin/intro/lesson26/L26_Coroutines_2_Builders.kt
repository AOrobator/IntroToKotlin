package com.orobator.kotlin.intro.lesson26

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

// Normal functions can't call suspend functions

// We get into the land of coroutines and suspending functions using
// coroutine builders.

// Launch - One of the most prominent coroutine builders. Fire & forget.
fun main0() {
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
fun main1() = runBlocking { // start main coroutine
    GlobalScope.launch { // launch a new coroutine in background and continue
        delay(1000L)
        println("World!")
    }
    println("Hello,")     // main coroutine continues here immediately
    delay(2000L)  // delaying for 2 seconds to keep JVM alive
}


// The async coroutine builder is used when we want to explicitly be
// concurrent.
//
// async returns a Deferred – a light-weight non-blocking future that
// represents a promise to provide a result later.
fun main2() = runBlocking {
    val time = measureTimeMillis {
        val one: Deferred<Int> = async { doSomethingUsefulOne() }
        // one.cancel() // Deferred also implements Job, a handle to
        // ongoing coroutine, so you can cancel.

        val two: Deferred<Int> = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")

    }
    println("Completed in $time ms")
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

//////////////////////////
// Structured concurrency

// Cancelling the parent cancels the children -> throws
// CancellationException to kids

// If a child fails with an exception other than
// CancellationException, this cancels the parent

// Parent Scope waits for children to complete

// Structured concurrency makes it harder to leak coroutines