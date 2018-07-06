package com.orobator.kotlin.intro.lesson5.solutions

// Create a class called Pool. Pool should take a maxFill: Int in the
// constructor as a read only property. By default, it should be 50,000.
// Also create a read/write property called gallons that only takes 0 and
// positive integers up to maxFill. By default, gallons should be half of
// maxFill.

// Give Pool a property called waterLevel: Int. Assume that the
// water level rises by 1ft every 5,000 gallons.
class Pool(val maxFill: Int = 50_000) {
    init {
        // Kotlin also has pre-conditions!
        require(maxFill > 0) { "maxFill must be positive" }
    }

    var gallons = maxFill / 2
        set(value) {
            if (value in 0..maxFill) {
                field = value
            }
        }

    // This is read-only to avoid creating a backing field
    val waterLevel get() = gallons / 5_000
}

// Create an alternate version of Pool called Pool2 that has the same
// requirements as above, and can take gallons as a constructor param
class Pool2(val maxFill: Int = 50_000, _gallons: Int) {
    init {
        // Kotlin also has pre-conditions!
        require(maxFill > 0) { "maxFill must be positive" }
    }

    var gallons = maxFill / 2
        set(value) {
            if (value in 0..maxFill) {
                field = value
            }
        }

    init {
        gallons = _gallons
    }

    // This is read-only to avoid creating a backing field
    val waterLevel get() = gallons / 5_000
}