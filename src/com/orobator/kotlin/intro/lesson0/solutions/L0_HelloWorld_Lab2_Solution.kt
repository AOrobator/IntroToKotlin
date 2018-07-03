package com.orobator.kotlin.intro.lesson0.solutions

fun main(args: Array<String>) {
    // Print out the 3rd character of "Kotlin"
    println("3rd character = ${"Kotlin"[2]}")

    // Declare an array of ints with the first 3 natural numbers
    val ints = intArrayOf(1, 2, 3)

    // Declare a String Array of length 5 with all nulls.
    // Hint: Check the standard library.
    val nullStrings = arrayOfNulls<String>(5)

    // Bonus! Declare a String that spells "Kotlin", but with all vowels removed.
    // No loops allowed!
    val vowels = setOf('a', 'e', 'i', 'o', 'u')
    val result = "Kotlin".filter { it !in vowels }
    println("Result: $result")
}