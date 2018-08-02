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