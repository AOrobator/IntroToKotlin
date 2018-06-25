package com.orobator.kotlin.intro

fun main(args: Array<String>) {

    ///////////
    /* Lists */
    ///////////

    // Immutable by default
    val listOfInts: List<Int> = listOf(1, 2, 3)

    var mutableInts: MutableList<Int> = listOfInts.toMutableList()
    mutableInts = mutableListOf(3, 4, 5)

    // If the underlying list is mutable, it can change
    val readOnlyView: List<Int> = mutableInts
    println("readOnlyView size: ${readOnlyView.size}") // prints 3

    mutableInts.clear()
    println("readOnlyView size: ${readOnlyView.size}") // prints 0!

    // Different ways to create a list
    val zeroToNine: List<Int> = List(size = 10, init = { it })
    println("zeroToNine: $zeroToNine")

    // Functional extensions available
    val threes: List<Int> = zeroToNine.filter { it % 3 == 0 }
    println("threes: $threes")

    val squares = listOf(1, 2, 3, 4).map { x -> x * x }
    println("squares: $squares")

    // List Concatenation
    val concat = listOf(1, 2) + listOf(3, 4)
    println("concat: $concat")
}