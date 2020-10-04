package com.orobator.kotlin.intro.lesson22.labs

// Pass a lambda to any function to check if the collection contains
// an even number.

fun containsEven(collection: Collection<Int>): Boolean =
        collection.any { TODO() }

// Define a function addN that returns a function of type (Int) -> Int
// It takes an Int n and returns a function that adds N to every input


// Implement factorial(n: Int): Int using fold
fun factorial(n: Int): Int = TODO()


// Define a function profile that takes in a parameter-less function
// that returns Unit.

// profile(...) should return the amount of milliseconds it took for
// the passed in function to run. Return type should be Long