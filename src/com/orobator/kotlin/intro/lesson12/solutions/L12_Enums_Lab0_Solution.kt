package com.orobator.kotlin.intro.lesson12.solutions

import com.orobator.kotlin.intro.lesson12.solutions.Doneness.RAW

// Declare a class Steak that extends class Meat
// Declare an enum Doneness with the following values:
// - Raw, Rare, Medium, Well Done, Charred

// Steak should have a property doneness that is readable by everyone,
// but can only be written to by instances of Steak. Steaks start out raw.

// Define a method cook() that increases the doneness level by 1,
// until it's Charred

// Create a main function that "cooks" a steak 10 times and prints out the
// doneness after each "cook".

enum class Doneness {
    RAW, RARE, MEDIUM, WELL_DONE, CHARRED
}

open class Meat

class Steak : Meat() {
    var doneness: Doneness = RAW
        private set

    fun cook() {
        val values = Doneness.values()
        val index = Math.min(doneness.ordinal + 1, values.lastIndex)
        doneness = values[index]
    }
}

fun main() {
    val nyStrip = Steak()
    for (i in 0..10) {
        println("Steak is ${nyStrip.doneness}")
        nyStrip.cook()
        println("Cooked steak to ${nyStrip.doneness}")
    }
}