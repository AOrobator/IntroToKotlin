package com.orobator.kotlin.intro.lesson1.solutions

fun main(args: Array<String>) {
    // Declare an immutable list of the first positive 1000 natural numbers
    val numbers = List(size = 1000) { it + 1 }

    // Declare an immutable list that filters the previous list to the first 26 natural numbers
    val first26 = numbers.filter { it < 27 }

    // Declare an immutable list that maps the previous list to the alphabet
    val alphabet = first26.map { (it + 64).toChar() }
    println(alphabet)

    // Update the value for key "a" to 42
    val alphaMap = mapOf("a" to 1, "b" to 2, "c" to 3)
    // This is an immutable type so we have to make it mutable
    val updated = alphaMap.toMutableMap()
    updated["a"] = 42
}