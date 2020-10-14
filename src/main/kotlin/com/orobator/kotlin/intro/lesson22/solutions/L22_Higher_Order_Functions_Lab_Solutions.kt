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

fun main() {
    val firstCollectionWithEvens = listOf(1, 2, 3)
    val secondCollectionWithEvens = setOf(2, 13, 1)
    println("Expecting containsEven(firstCollectionWithEvens) to be true, is actually ${containsEven(firstCollectionWithEvens)}")
    println("Expecting containsEven(secondCollectionWithEvens) to be true, is actually ${containsEven(secondCollectionWithEvens)}")

    val firstCollectionNoEvens = emptyList<Int>()
    val secondCollectionNoEvens = setOf(3, 7, 9)
    println("Expecting containsEven(firstCollectionNoEvens) to be false, is actually ${containsEven(firstCollectionNoEvens)}")
    println("Expecting containsEven(secondCollectionNoEvens) to be false, is actually ${containsEven(secondCollectionNoEvens)}\n")

    val addFive = addN(5)
    println("addFive(5) should be 10, is actually ${addFive(5)}")
    println("addFive(1) should be 6, is actually ${addFive(1)}")

    val addTen = addN(10)
    println("addTen(5) should be 15, is actually ${addTen(5)}\n")

    println("factorial(3) should be 6, is actually ${factorial(3)}")
    println("factorial(5) should be 120, is actually ${factorial(5)}\n")

    val sortRuntime = profile {
        for (i in 1..100_000) {
            listOf(5, 2, 8, 3, 74, 2, 68, 472, 6, 46, 8, 33, 3, 56, 78, 2, 4).sorted()
        }
    }

    val quickRuntime = profile {
        val foo = 4 + 1
    }
    println("Runtime for first profile is $sortRuntime")
    println("Runtime for second profile is $quickRuntime")
}