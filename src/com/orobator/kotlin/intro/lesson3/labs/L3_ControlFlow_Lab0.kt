package com.orobator.kotlin.intro.lesson3.labs

// Write a method that calculates the nth 2 digit prime
fun main(args: Array<String>) {
    println("nthTwoDigitPrime(0) should equal 11 and is ${nthTwoDigitPrime(0)}")
    println("nthTwoDigitPrime(1) should equal 13 and is ${nthTwoDigitPrime(1)}")
    println("nthTwoDigitPrime(7) should equal 37 and is ${nthTwoDigitPrime(7)}")
}

fun nthTwoDigitPrime(n: Int): Int = 0