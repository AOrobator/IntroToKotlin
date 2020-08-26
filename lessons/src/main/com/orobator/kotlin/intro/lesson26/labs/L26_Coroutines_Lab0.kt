package com.orobator.kotlin.intro.lesson26.labs

import kotlinx.coroutines.*


// Implement log which should print out the log message as well as the
// current thread of execution.
//
// Log Format:
// Thread: <thread_name>, Msg: <msg>
//
// Then run main. Observe what thread each block runs in.
fun log(msg: String): Unit = println("Thread: ${Thread.currentThread().name}, Msg: $msg")

fun main() = runBlocking {
    log("Starting off")
    val job: Job = GlobalScope.launch {
        // launch a new coroutine in background and continue
        log("In launch")
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
        log("In launch, after delay")
    }
    println("Hello,") // main thread continues while coroutine is delayed
    log("About to join")
//    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
    job.join()

    log("Have joined, returning from main")
}