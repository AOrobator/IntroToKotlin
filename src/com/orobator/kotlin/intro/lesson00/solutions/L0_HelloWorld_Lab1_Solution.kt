package com.orobator.kotlin.intro.lesson00.solutions

fun main() {
    // Write a for loop from 0 to 15 that jumps by 3's using an Int range.
    // Print out the loop index on each iteration.
    for (i in 0..15 step 3) {
        println("i = $i")
    }

    // Declare a boolean value that's the result of checking
    // if 42 is between 30 and 50. Print the value.
    val inRange: Boolean = 42 in 30..50
    println("inRange: $inRange")

    // Write a for loop from 100 to 0 counting by 25's.
    // Print out the loop index on each iteration.
    for (i in 100 downTo 0 step 25) {
        println("i = $i")
    }
}