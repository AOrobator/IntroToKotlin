package com.orobator.kotlin.intro

/**
 * @param args Command Line arguments
 */
fun main(args: Array<String>) {
    val scope = "World"
    println("Hello $scope!") // Kotlin supports String interpolation

    ////////////////
    /* val vs var */
    ////////////////

    val name = "Andrew" // read-only variable
    var age = 24 // read & write variable

    age = 25 // Allowed, because this is a var
    // name = "Dan" // Not allowed because this is a val


    ////////////////////
    /* Type Inference */
    ////////////////////

    val pet: String = "Husky" // Can optionally specify the type
    val food = "Tomato" // This is fine as well. The compiler knows this is a String


    /////////////////
    /* Basic Types */
    /////////////////

    val myDouble: Double = 42.0 // 64 bits wide
    val myFloat: Float = 42f // 32 bits wide

    val myLong: Long = 42L // 64 bits wide
    val myInt: Int = 42 // 32 bits wide
    val myShort: Short = 42 // 16 bits wide
    val myByte: Byte = 42 // 8 bits wide

    // Everything is an object! Smaller types not subtypes of larger types.

    // val impossible: Long = myInt
    val castedLong: Long = myInt.toLong()
    println("Casted Long = $castedLong")

    // Numbers are represented as primitives unless generics or nullability is involved.
}