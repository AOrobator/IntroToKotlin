package com.orobator.kotlin.intro.lesson3.solutions

// Write a method that calculates the nth 2 digit prime
fun main(args: Array<String>) {
    println("nthTwoDigitPrime(0) should equal 11 and is ${nthTwoDigitPrime(0)}")
    println("nthTwoDigitPrime(1) should equal 13 and is ${nthTwoDigitPrime(1)}")
    println("nthTwoDigitPrime(7) should equal 37 and is ${nthTwoDigitPrime(7)}")
}

fun nthTwoDigitPrime(n: Int): Int {
    var found = 0
    var guess = 0

    while (found <= n) {
        guess++
        if (isTwoDigitPrime(guess)) {
            found++
        }
    }

    return guess
}

fun isTwoDigitPrime(n: Int): Boolean = (n in 10..99) && isPrime(n)

fun isPrime(n: Int): Boolean {
    if (n < 2) return false

    for (factor in 2 until n) {
        if (n % factor == 0) {
            return false
        }
    }

    return true
}