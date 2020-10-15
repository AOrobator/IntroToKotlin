package com.orobator.kotlin.intro.lesson25.solutions

// Create a class in Java called DogPojo. It should have a private field name
// that's a string. name should have a getter and a setter. Instantiate DogPogo
// from Kotlin. Note how you can call the getter and setter for "name" in the
// traditional Java way as well as Kotlin's property syntax.

fun main() {
    val doggo = DogPojo()

    // Traditional Java syntax
    doggo.setName("Rex")
    println("Name = ${doggo.getName()}")

    // Property Syntax
    doggo.name = "Chapman"
    println("Name = ${doggo.name}")

    // Platform Type RunTimeException. We declare this as non-null, but the Java
    // side always returns null.
    val breed: String = doggo.breed
}

// What is the biggest difference between Java and Kotlin's exception
// handling? How does this difference affect interoperability? What are some
// things you can do to address this?

// Kotlin does not have checked exceptions, so does not have to explicitly
// handle exceptions. This also means that you can't normally indicate to a Java
// function that an exception might be thrown. You can either modify the return
// type so that it can return the exception instead of throwing it, or annotate
// the Kotlin method with @Throws

// Create an example of a platform type throwing a runtime error when consumed
// from Kotlin.

fun platformRunTimeException() {
    val doggo = DogPojo()
    val breed: String = doggo.breed
}