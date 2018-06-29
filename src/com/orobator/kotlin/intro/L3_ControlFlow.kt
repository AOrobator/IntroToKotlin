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
}