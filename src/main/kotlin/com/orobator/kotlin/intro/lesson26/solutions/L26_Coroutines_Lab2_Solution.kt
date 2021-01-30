package com.orobator.kotlin.intro.lesson26.solutions

import java.io.IOException

// Given an API with callbacks convert it to use coroutines
interface ReadTextFileCallback {
    fun onSuccess(text: String)
    fun onError(exception: Exception)
}

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
}