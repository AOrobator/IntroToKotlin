package com.orobator.kotlin.intro.lesson11

// Data classes
// Kotlin's version of a POJO
// Automatically implements toString(), hashCode(), and equals()
data class Animal(val numLegs: Int, val color: String)

// Primary constructor needs to have at least one parameter
// Each parameter in the primary constructor needs to be a val or a var
// Data classes can't be abstract, open, sealed or inner

// If there are explicit implementations of equals(), hashCode() or toString()
// in the data class body or final implementations in a superclass, then these
// functions are not generated, and the existing implementations are used.

// If the class needs to have a parameter-less constructor, give default values
// for all properties.
data class User(val name: String = "", val age: Int = 0)

// The compiler only uses the properties in the primary constructor for the
// generated methods. Properties in the class body won't be used.
data class Creature(val numLegs: Int) {
    var numEyes: Int = 2
}

fun main(args: Array<String>) {
    val thing1 = Creature(numLegs = 2)
    val thing2 = Creature(numLegs = 2).also { it.numEyes = 7 }

    // These creatures have a different numEyes but numLegs is the only
    // property used in equals()
    println("Thing1 == Thing2? ${thing1 == thing2}")

    // numLegs is the only property used in toString() as well
    println("thing1: $thing1")
    println("thing2: $thing2")

    // All data classes have a built in copy() function
    // It allows you to create a new copy of the class that may be
    // slightly different
    // Very useful in tests where you want to assert a variety of values
    val snake = thing2.copy(numLegs = 0)

    // Destructuring
    val frog = Animal(numLegs = 4, color = "green")
    // Can break things down into their components
    val (legs, color) = frog

    println("Legs = $legs, Color = $color")

    // This is possible because functions are generated:
    // component1(), component2(), ..., componentN()

    // Only care about first N of values? Leave the rest out!
    val (frogLegs) = frog
    println(frogLegs)

    // Only care about some values? Use an underscore!
    val (_, frogColor) = frog
    println("frogColor = $frogColor")

    // Kotlin provides Pair and Triple, but named data classes will usually
    // be better
    val intStringPair: Pair<Int, String> = Pair(0, "")

    // Alternate way of declaring a pair
    val intStringPair2: Pair<Int, String> = 0 to ""

    val xyzCoord: Triple<Int, Int, Int> = Triple(4, 8, 15)
}