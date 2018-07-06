package com.orobator.kotlin.intro.lesson5.solutions

// Create a class called Pool. Pool should take a maxFill: Int in the
// constructor as a read only property. Also create a read/write property
// called gallons that only takes 0 and positive integers up to maxFill.
// By default, gallons should be half of maxFill.

// Give Pool a property called waterLevel: Int. Assume that the
// water level rises by 1ft every 20 gallons.
class Pool(val maxFill: Int) {
    var gallons = maxFill / 2
        set(value) {
            if (value in 0..maxFill) {
                field = value
            }
        }

    // This is read-only to avoid creating a backing field
    val waterLevel get() = gallons / 20
}

