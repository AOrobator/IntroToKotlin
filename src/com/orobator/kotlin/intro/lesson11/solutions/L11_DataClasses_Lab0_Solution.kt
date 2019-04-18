package com.orobator.kotlin.intro.lesson11.solutions

// Person.java converted to Kotlin
data class Person(val firstName: String, val lastName: String, val age: Int)

// Create a data class called Car with the following read only properties:
// Year (Int), Manufacturer (String), Wheels (Int)
// It should have Color (String) as a read/write property
// Two cars should be equal if the year, manufacturer, and wheels are the same,
// even if the color is different.
data class Car(val year: Int, val manufacturer: String, val wheels: Int) {
    var color: String = "black"
}

// Write code that copies one car, but with a different year and wheel count
fun main() {
    val tesla = Car(year = 2016, manufacturer = "Tesla", wheels = 4)
    val beater = tesla.copy(year = 2012, wheels = 3)

// Write code that uses destructuring to extract the manufacturer from a Car
    val (_, manufacturer) = tesla
    println(manufacturer)
}

