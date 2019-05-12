package com.orobator.kotlin.intro.lesson22.solutions

// Pass a lambda to any function to check if the collection contains
// an even number.

fun containsEven(collection: Collection<Int>): Boolean =
        collection.any { it % 2 == 0 }

// Define a function addN that returns a function of type (Int) -> Int
// It takes an Int n and returns a function that adds N to every input
fun addN(n: Int): (Int) -> Int {
    return fun(y: Int): Int = n + y
}

// Implement factorial(n: Int): Int using fold
fun factorial(n: Int): Int = (1..n).fold(1, Int::times)


// Define a function profile that takes in a parameter-less function
// that returns Unit.

// profile(...) should return the amount of milliseconds it took for
// the passed in function to run. Return type should be Long
fun profile(block: () -> Unit): Long {
    val startTime = System.currentTimeMillis()
    block()
    val endTime = System.currentTimeMillis()
    return endTime - startTime
}