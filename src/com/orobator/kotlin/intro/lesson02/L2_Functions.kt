package com.orobator.kotlin.intro.lesson02


fun main(arguments: Array<String>) {
    // fun keyword used to start functions
    // paramName: Type
    // return type at the end of the declaration
    // Unit doesn't return anything, similar to Void in Java.
    fun maybePrintLength(any: Any): Unit {
        if (any is String) { // is: type check operator (instanceof in Java)
            println("$any's length is ${any.length}") // Any is smart casted to a string
        } else {
            println("$any isn't a String")
        }
    } // This function is also local (defined in another function)

    maybePrintLength(0)
    maybePrintLength("Hello World")

    println()

    // Single Line Expressions
    fun add(x: Int, y: Int): Int = x + y

    fun subtract(x: Int, y: Int) = x - y // Return type is inferred by compiler

    println("1 + 1 = ${add(1, 1)}")
    println("3 - 2 = ${subtract(3, 2)}")

    // Named parameters allow any param order
    println("2 - 3 = ${subtract(y = 3, x = 2)}")

    println()

    // Default Arguments allow for less overloaded functions
    fun printDefaults(foo: Int = 42) {
        println("printDefaults(foo = $foo)")
    }

    printDefaults(100)
    printDefaults()

    println()

    // For multiple arguments, the defaults are usually last, otherwise you're forced to use named params
    fun printDefaults2(foo: Int, bar: String = "Bar", baz: Double = 12.0) {
        println("printDefaults2(foo = $foo, bar = $bar, baz = $baz)")
    }

    printDefaults2(3)

    // Kotlin supports variable number of arguments
    fun <T> asList(vararg ts: T): List<T> { // Only one param can be vararg
        val result = ArrayList<T>()
        for (t in ts) // ts is an Array
            result.add(t)
        return result
    } // A hint of generics. To be covered later...

    val list1 = asList(0, 1, 2)
    val list2 = asList(0, 1, 2, 3, 4, 5)

    // Spread operator takes array and can pass them to a vararg param
    val spreadThis: Array<Int> = arrayOf(1, 3, 6)
    asList(*spreadThis)
}
