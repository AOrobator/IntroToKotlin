package com.orobator.kotlin.intro.lesson26

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//////////////////////////////////////
// Cancellation & Exception handling

// See https://kotlinlang.org/docs/exception-handling.html

fun main() {
    cancelDemo()
    nonCooperativeCancellationDemo()
    cooperativeCancellationDemo()
}

//////////////////////////////////
// Cancelling coroutine execution

// In a long-running application you might need fine-grained control on your
// background coroutines. For example, a user might have closed the page that
// launched a coroutine and now its result is no longer needed and its operation
// can be cancelled. The launch function returns a Job that can be used to
// cancel the running coroutine:

fun cancelDemo() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
    job.join() // waits for job's completion
    println("main: Now I can quit.")
}

// It produces the following output:
//
// job: I'm sleeping 0 ...
// job: I'm sleeping 1 ...
// job: I'm sleeping 2 ...
// main: I'm tired of waiting!
// main: Now I can quit.
//
// As soon as main invokes job.cancel, we don't see any output from the other
// coroutine because it was cancelled. There is also a Job extension function
// cancelAndJoin that combines cancel and join invocations.


///////////////////////////////
// Cancellation is cooperative

fun nonCooperativeCancellationDemo() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

// Run it to see that it continues to print "I'm sleeping" even after
// cancellation until the job completes by itself after five iterations.


///////////////////////////////////////
// Making computation code cancellable

// There are two approaches to making computation code cancellable. The first
// one is to periodically invoke a suspending function that checks for
// cancellation. There is a yield function that is a good choice for that
// purpose. The other one is to explicitly check the cancellation status, which
// is the approach we'll look into now.

// Here, we're taking the non-cooperative example above, and replacing
// "while (i < 5)" with "while (isActive)"

fun cooperativeCancellationDemo() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

// Review exception behavior for CoroutineScope.launch
// SupervisorJob vs regular (parent/child relationship)
// Exception behavior for async
// CoroutineExceptionHandler

// Lab - Write code that does cooperative cancellation. Maybe compute fib(n)
