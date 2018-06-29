package com.orobator.kotlin.intro

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
}