package com.orobator.kotlin.intro.lesson1

fun main(args: Array<String>) {
    // Declare a list of the first 1000 natural numbers
    val numbers = List(size = 1000) { it }

    // Filter the list to the first 26 natural numbers
    val first26 = numbers.filter { it < 26 }

    // Map the list to the alphabet
    val alphabet = first26.map { (it + 65).toChar() }
    println(alphabet)

    // Update the value for key "a" to 42
    val alphaMap = mapOf("a" to 1, "b" to 2, "c" to 3)
    // This is an immutable type so we have to make it mutable
    val updated = alphaMap.toMutableMap()
    updated["a"] = 42
}