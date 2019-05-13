package com.orobator.kotlin.intro.lesson26.labs

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


// Implement log which should print out the log message as well as the
// current thread of execution.
//
// Log Format:
// Thread: <thread_name>, Msg: <msg>
//
// Then run main. Observe what thread each block runs in.
fun log(msg: String): Unit = TODO()

fun main() {
    log("Starting off")
    GlobalScope.launch { // launch a new coroutine in background and continue
        log("In launch")
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
        log("In launch, after delay")
    }
    println("Hello,") // main thread continues while coroutine is delayed
    log("About to sleep")
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}