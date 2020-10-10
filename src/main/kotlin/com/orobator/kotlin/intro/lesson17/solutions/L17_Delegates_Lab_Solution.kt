package com.orobator.kotlin.intro.lesson17.solutions

import kotlin.properties.Delegates

// Create a class Dog.
// Give Dog a read/write property name initialized to Fido.
// Give Dog a property nameChangedCount that counts how many times name has been
// changed.
// Give Dog a property isConfused that's true once it's name changes 3+ times
// A Dog's name is NOT allowed to be "Cat"

class Dog {
    var nameChangedCount = 0
        private set

    var name: String by Delegates.vetoable("Fido") { property, oldValue, newValue ->
        if (newValue == "Cat") false else {
            nameChangedCount++
            true
        }
    }

    val isConfused: Boolean get() = nameChangedCount >= 3
}

fun main() {
    val dog = Dog()
    println("Dog name should be Fido, is ${dog.name}")
    println("Name changed count should be 0, is ${dog.nameChangedCount}")
    println("Is confused should be false, is ${dog.isConfused}\n")

    dog.name = "Ben"
    println("Dog name should be Ben, is ${dog.name}")
    println("Name changed count should be 1, is ${dog.nameChangedCount}")
    println("Is confused should be false, is ${dog.isConfused}\n")

    dog.name = "Rex"
    println("Dog name should be Rex, is ${dog.name}")
    println("Name changed count should be 2, is ${dog.nameChangedCount}")
    println("Is confused should be false, is ${dog.isConfused}\n")

    dog.name = "Fifi"
    println("Dog name should be Fifi, is ${dog.name}")
    println("Name changed count should be 3, is ${dog.nameChangedCount}")
    println("Is confused should be true, is ${dog.isConfused}\n")

    dog.name = "Cat"
    println("Dog name should be Fifi, is ${dog.name}")
    println("Name changed count should be 3, is ${dog.nameChangedCount}")
    println("Is confused should be true, is ${dog.isConfused}\n")
}