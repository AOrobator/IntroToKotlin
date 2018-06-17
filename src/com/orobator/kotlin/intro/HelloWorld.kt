package com.orobator.kotlin.intro

/**
 * @param args Command Line arguments
 */
fun main(args: Array<String>) {
    val scope = "World"
    println("Hello $scope!") // Kotlin supports String interpolation

    /* val vs var */
    val name = "Andrew" // read-only variable
    var age = 24 // read & write variable

    age = 25 // Allowed, because this is a var
    // name = "Dan" // Not allowed because this is a val


    /* Type Inference */
    val pet: String = "Husky" // Can optionally specify the type
    val food = "Tomato" // This is fine as well. The compiler knows this is a String

}