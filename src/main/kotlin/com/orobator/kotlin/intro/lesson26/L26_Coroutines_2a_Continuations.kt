package com.orobator.kotlin.intro.lesson26

import kotlinx.coroutines.delay

// Show Kotlin Bytecode -> Decompile to Java
// How is this method signature different from how we defined it?
suspend fun getDataFromNetwork(id: String): String {
    delay(1000L)
    return "$id: Data"
}

// Compiler will add kotlin.coroutines.Continuation to method call

// Basically a callback
// Success: Continuation<T>.resume(value: T)
// Error: Continuation<T>.resumeWithException(exception: Throwable)

// Continuations are responsible for storing information about the callstack
// such as knowing where to resume execution after being paused. This allows
// coroutines to be suspended without blocking the parent thread.
