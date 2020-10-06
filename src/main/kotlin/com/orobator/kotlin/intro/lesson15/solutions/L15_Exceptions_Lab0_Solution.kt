package com.orobator.kotlin.intro.lesson15.solutions

import java.io.IOException
import java.lang.Exception
import java.net.URL

// Figure out what kind of exception can be thrown here by diving into the
// source code. Write a try-catch block to prevent crashing when an exception is
// thrown. In the catch block, print that you caught the exception.
fun main () {
    // Stepping into the code reveals several places where an IOException is
    // thrown. For example, readBytes' call to openStream potentially throws an
    // IOException.

    try {
        val str = URL("http://numbersapi.com/100").readText()
        println(str)
    } catch (exception: IOException) {
        println("Caught an exception")
    }
}

// Write a function safeReadText that takes in a string representing a URL and
// returns a Result. You'll create a StringResult sealed class that is either a
// Success containing the string read from the network or a Failure containing
// the exception that was thrown

sealed class StringResult {
    data class Success(val value: String): StringResult()
    data class Failure(val exception: Exception): StringResult()
}

fun safeReadText(url: String): StringResult {
    return try {
        val str = URL(url).readText()
        StringResult.Success(str)
    } catch (exception: IOException) {
        StringResult.Failure(exception)
    }
}

fun safeReadTextTest() {
     val safeStr: StringResult = safeReadText("http://numbersapi.com/100")
     return when (safeStr) {
         is StringResult.Success -> println(safeStr.value)
         is StringResult.Failure -> safeStr.exception.printStackTrace()
     }
}

// Write a function called fun myTodo(reason: String): Nothing that throws a
// NotImplementedError and places the reason in the exception thrown. This will
// be very similar to the TODO() function.

fun myTodo(reason: String): Nothing = throw NotImplementedError(message = "Not implemented: $reason")

// Observe what happens when you try to call code after calling myTodo() or
// TODO(). Why does this happen?

// The code called below myTodo() is highlighted and marked as unreachable. This
// is because functions with a return type of Nothing don't return, so nothing
// after it can be executed.