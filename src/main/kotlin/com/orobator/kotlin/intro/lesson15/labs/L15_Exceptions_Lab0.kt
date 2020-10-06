package com.orobator.kotlin.intro.lesson15.labs

import java.net.URL

// Figure out what kind of exception can be thrown here by diving into the
// source code. Write a try-catch block to prevent crashing when an exception is
// thrown. In the catch block, print that you caught the exception.
fun main () {
    val str = URL("http://numbersapi.com/100").readText()
    println(str)
}

// Write a function safeReadText that takes in a string representing a URL and
// returns a Result. You'll create a StringResult sealed class that is either a
// Success containing the string read from the network or a Failure containing
// the exception that was thrown

fun safeReadTextTest() {
    // val safeStr: StringResult = safeReadText("http://numbersapi.com/100")
    // return when (safeStr) {
    //     Success -> println(safeStr.value)
    //     Failure -> safeStr.exception.printStackTrace()
    // }
}

// Write a function called fun myTodo(reason: String): Nothing that throws a
// NotImplementedError and places the reason in the exception thrown. This will
// be very similar to the TODO() function.

// Observe what happens when you try to call code after calling myTodo() or
// TODO(). Why does this happen?
