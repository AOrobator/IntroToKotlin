package com.orobator.kotlin.intro.lesson26

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//////////////////////////////////////
// Cancellation & Exception handling

// See https://kotlinlang.org/docs/exception-handling.html

fun main() {
    cancelDemo()
}

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

// Cancellation is cooperative
  // - show example of child not cancelling when parent cancels
// Review exception behavior for CoroutineScope.launch
// SupervisorJob vs regular (parent/child relationship)
// Exception behavior for async
// CoroutineExceptionHandler

// Lab - Write code that does cooperative cancellation. Maybe compute fib(n)
