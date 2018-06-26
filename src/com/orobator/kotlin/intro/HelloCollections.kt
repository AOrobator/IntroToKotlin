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

    // Can prevent changes by copying the list
    val readyOnlyCopy: List<Int> = mutableInts.toList()
    println("readyOnlyCopy size: ${readyOnlyCopy.size}")

    println("\nClearing mutable list...\n")
    mutableInts.clear()
    println("readOnlyView size: ${readOnlyView.size}") // prints 0!
    println("readyOnlyCopy size: ${readyOnlyCopy.size}") // prints 3!

    println()

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
    println("concat: $concat\n")


    //////////
    /* Maps */
    //////////

    // Also immutable by default
    val alphaMap: Map<String, Int> = mapOf("a" to 1, "b" to 2, "c" to 3) // Only use in non-critical code-paths
    for ((k, v) in alphaMap) {
        println("Key: $k, Value: $v")
    }

    val readWriteMap = hashMapOf("foo" to 1, "bar" to 2)
    println("readWriteMap[\"foo\"]: ${readWriteMap["foo"]}")  // prints "1"

    val snapshot: Map<String, Int> = HashMap(readWriteMap)

    readWriteMap["foo"] = 42
    println("readWriteMap[\"foo\"]: ${readWriteMap["foo"]}")  // prints "42"
    println("snapshot[\"foo\"]: ${snapshot["foo"]}")  // prints "1"

}
