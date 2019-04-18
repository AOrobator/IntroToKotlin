package com.orobator.kotlin.intro.lesson15

import java.io.IOException
import java.lang.IllegalStateException
import java.lang.Integer.parseInt

fun main() {
    // Exceptions

    // All exception classes in Kotlin are descendants of the class Throwable.
    // Every exception has a message, stack trace and an optional cause.

    // Throw an exception using 'throw' keyword

    try { // try-catch-finally block similar to Java's
        throw IllegalStateException()
    } catch (e: IllegalStateException) {
        e.printStackTrace()
    } finally {
        println("Finally reached the bottom")
    }

    // There can be zero or more catch blocks
    // There needs to be at least one catch or finally block

    // try is an expression, so it has a return value
    val a: Int? = try {
        parseInt("42")
    } catch (e: NumberFormatException) {
        println("Got exception")
        null // Expression evaluates to last value
    }

    // Finally blocks don't affect the result of the expression

    // Kotlin lacks checked exceptions
    // In Java you're forced to declare whether you throw an exception, but
    // not in Kotlin

    // An example interface of the JDK implemented by StringBuilder class:
    // Appendable append(CharSequence csq) throws IOException;

    // Every time we append, we have to account for an exception being thrown,
    // even if the function call is safe.

    // End up with a lot of empty try-catch blocks

    // Two options to expose exceptions

    @Throws(IOException::class)
    fun readFile() = Unit

    // @Throws annotation specifies what exceptions you throw. These will be
    // included in the JVM method signature

}

// Could return a special type that indicates success or failure
sealed class Try<T> {
    data class Failure<T>(val e: Exception) : Try<T>()
    data class Success<T>(val value: T) : Try<T>()
}

// This is already provided in Arrow, Kotlin's de facto functional programming
// library. Comes with lots of helper methods as well

// getOrDefault(), filter()

// Nothing

class Person(val name: String?)

fun nothingDemo() {
    val p = Person(null)

    // throw is an expression in Kotlin
    val n = p.name ?: throw IllegalArgumentException("Name required")

    // A throw expression has type Nothing
    // Nothing has no values
    // Used to mark places in code that can't be reached

    // Can be used to mark a method that never returns
    fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }

    val s = p.name
    println(s)     // 's' is known to be initialized at this point

    // Nothing is a special class because it's at the bottom of Kotlin's type
    // hierarchy, opposite of Any.

    // Nothing extends all classes, which is why you can throw an exception
    // anywhere

    // Might also see Nothing with type inference
    // Nothing? has 1 possible value, null
    val x = null // Implicit type of Nothing?
    val l = listOf(null)   // l has type `List<Nothing?>
}

