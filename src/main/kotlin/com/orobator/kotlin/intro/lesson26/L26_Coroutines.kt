package com.orobator.kotlin.intro.lesson26

import kotlinx.coroutines.*

fun main() = runBlocking {
    // withContext allows switching the coroutine context for a block of code
    launch(Dispatchers.Default) {
        println("Default dispatcher: ${coroutineContext[CoroutineDispatcher]}")

        withContext(Dispatchers.IO) {
            println("IO dispatcher: ${coroutineContext[CoroutineDispatcher]}")
        }

        println("Back to Default: ${coroutineContext[CoroutineDispatcher]}")
    }
}
