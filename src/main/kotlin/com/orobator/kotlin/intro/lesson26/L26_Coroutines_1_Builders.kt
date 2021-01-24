package com.orobator.kotlin.intro.lesson26

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

// Normal functions can't call suspend functions, only a suspending function or
// a coroutine can.
fun normalFunction() {
    // Delay is a suspending function, similar to Thread.sleep()
//    delay(1_000L) // -> Compiler error: Suspend function 'delay' should
    //                           be called only from a coroutine or another
    //                           suspend function.
}

// We get into the land of coroutines and suspending functions using
// coroutine builders.

// Launch - One of the most prominent coroutine builders. Fire & forget.
// Personally, I find myself using launch much more than any of the other
// coroutine builders.
fun main0() {
    GlobalScope.launch {
        // launch a new coroutine in background and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}

// Runs a new coroutine and blocks the current thread interruptibly
// until its completion.
//
// This function should not be used from a coroutine.
//
// It is designed to bridge regular blocking code to libraries that
// are written in suspending style, to be used in main functions and
// in tests.
fun main1() = runBlocking {
    // start main coroutine
    GlobalScope.launch {
        // launch a new coroutine in background and continue
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
fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one: Deferred<Int> = async {
            withTimeout(500L) {
                doSomethingUsefulOne()
            }
        }
//         one.cancel() // Deferred also implements Job, a handle to
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