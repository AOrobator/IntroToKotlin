package com.orobator.kotlin.intro.lesson26.solutions

import kotlinx.coroutines.runBlocking
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Given the [readFile] function and the [ReadTextFileCallback] interface,
 * implement the function [coReadFile] that suspends instead of using a
 * callback. Run your suspending function in a [runBlocking] coroutine scope in
 * the main function.
 */
interface ReadTextFileCallback {
    fun onSuccess(text: String)
    fun onError(exception: Exception)
}

/**
 * Let's pretend this function actually does file IO and fails about half the
 * time.
 */
fun readFile(path: String, callback: ReadTextFileCallback) {
    val currentTime = System.currentTimeMillis().toInt()
    if (currentTime % 2 == 0) {
        callback.onSuccess("$path File Contents")
    } else {
        callback.onError(IOException())
    }
}

fun main() {
    readFile("test-path", object : ReadTextFileCallback {
        override fun onSuccess(text: String) {
            println("Received $text")
        }

        override fun onError(exception: Exception) {
            println("Got error $exception")
        }
    })

    // Code Here 👇
    runBlocking {
        val text: String = try {
            coReadFile("coroutine-test-path")
        } catch (exception: Exception) {
            "Got error $exception"
        }
        println("Coroutine received $text")
    }
}

suspend fun coReadFile(path: String): String = suspendCoroutine { continuation ->
    readFile(path, object : ReadTextFileCallback {
        override fun onSuccess(text: String) {
            continuation.resume(text)
        }

        override fun onError(exception: Exception) {
            continuation.resumeWithException(exception)
        }
    })
}