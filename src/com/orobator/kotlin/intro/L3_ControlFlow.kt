package com.orobator.kotlin.intro

import java.lang.Integer.parseInt

fun main(args: Array<String>) {
    // If statements are expressions. They have a value
    // This means no ternary operator
    val foo = "Hello"
    val lengthParity = if (foo.length % 2 == 0) "even" else "odd"

    // If statements with blocks
    var weather = "Sunny"
    val clothes = if (weather == "Sunny") {
        println("It's a great day!")
        "tank" // Last statement is the value
    } else { // When assigned to a value, if must have else. Value needs to be determined!
        println("Stay dry!")
        "hoodie"
    }


    // When statement, replaces switch statement
    when (weather) {
        "Sunny" -> println("Bring your shades!")
        "Rainy" -> println("Bring an umbrella!")
        else -> { // Can optionally use a block
            println("Better grab a hoodie just in case")
        }
    }

    var bar = when (weather.length) {
        0 -> 3
        1 -> 7
        3 -> 11
        else -> 12 // Must have else as expression unless compiler can tell that all cases are covered (enums and sealed classes)
    }

    // When statements don't "fall through"
    // Have to explicitly state when you want multiple conditions to map to the same logic
    var animal = "Husky"
    when (animal) {
        "Husky", "Shiba Inu" -> println("Animal is a dog")
        else -> println("Unknown animal")
    }

    // Don't have to use constants in a when expression
    var x: Any = 42
    when (x) {
        parseInt("24") -> print("s encodes x")
        is String -> println(x.length)
        in 0..9 -> println("Is a digit")
        !in 13..19 -> println("Not a teen")
        else -> println("s does not encode x")
    }

    // When can also replace if-else-if chains
    when {
        animal.length > 42 -> println("Case 1")
        x is String -> println("Case 2")
        animal == "Husky" -> println("woof")
    } // Don't need else because this isn't an expression


    // For loops
    // Use ranges to iterate through numbers
    for (i in 0..10) {
        print("*")
    }

    println()

    for (i in 15 downTo 0 step 3) {
        when (i) {
            0 -> print(0)
            else -> print("$i, ")
        }
    }

    println()

    val list = listOf(1, 2, 3, 4, 5)
    for (int in list) { // Iterates through anything that provides an Iterator
        println(int)
    }

    // Can be on a single line
    for (myInt: Int in list) print(myInt)

    println()

    for (i in list.indices) print(list[i])

    println()

    for ((index, value) in list.withIndex()) {
        println("Element at index $index is $value")
    }

    list.forEach { println("For each $it") }


    // While/Do-while loops
    var i = 10
    while (i > 0) {
        i--
    }


    do {
        i++
    } while (i < 5)

    println("i = $i")

    i = 10

    // Labeled loops
    outer@ while (i > 0) {
        i--

        if (i % 2 == 0) {
            continue
        } else {
            println("$i is odd")
        }

        for (k in 1..3) {
            if (k * i == 6) {
                break@outer
            }
        }
    }

    println()

    // Return at labels
    (1..4).forEach loop@{
        if (it == 2) return@loop
        println("Explicit labeled return $it")
    }

    println()

    listOf(1, 2, 3, 4).forEach {
        if (it == 2) return@forEach
        println("Implicit labeled return $it")
    }

}