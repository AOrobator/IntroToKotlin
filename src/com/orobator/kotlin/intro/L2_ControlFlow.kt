package com.orobator.kotlin.intro


fun main(arguments: Array<String>) {
    maybePrintLength(0)
    maybePrintLength("Hello World")

    println()

    println("1 + 1 = ${add(1, 1)}")
    println("3 - 2 = ${subtract(3, 2)}")

    // Named parameters allow any param order
    println("2 - 3 = ${subtract(y = 3, x = 2)}")
}

// fun keyword used to start functions
// paramName: Type
// return type at the end of the declaration
// Unit doesn't return anything, similar to Void in Java.
fun maybePrintLength(any: Any): Unit {
    if (any is String) { // is: type check operator
        println("$any's length is ${any.length}") // Any is smart casted to a string
    } else {
        println("$any isn't a String")
    }
}

// Single Line Expressions
fun add(x: Int, y: Int): Int = x + y

fun subtract(x: Int, y: Int) = x - y // Return type is inferred by compiler