package com.orobator.kotlin.intro.lesson13.solutions

import com.orobator.kotlin.intro.lesson13.solutions.Expression.*

// Create a sealed class called Expression
// It should have two possible subtypes, a Num, that has a single int property,
// and Sum which has two Expression properties, left and right

// Write a method call eval() that takes in an Expression and returns the int
// value of that expression

// Create another subtype of Expression called Multiply which has two Expression
// properties, left and right. eval() should be implemented in such a way that
// this caused a compile error. Fix it.

sealed class Expression {
    data class Num(val value: Int) : Expression()
    data class Sum(val left: Expression, val right: Expression) : Expression()
    data class Multiply(val left: Expression, val right: Expression) : Expression()
}

fun eval(e: Expression): Int = when (e) {
    is Num -> e.value
    is Sum -> eval(e.left) + eval(e.right)
    is Multiply -> eval(e.left) * eval(e.right)
}

fun main() {
    val one = Num(value = 1)
    val sum = Sum(left = one, right = one)
    println("Eval of $sum is ${eval(sum)}, should be 2")

    val two = Num(2)
    val three = Num(3)
    val multiplication = Multiply(left = two, right = three)
    println("Eval of $multiplication is ${eval(multiplication)}, should be 6")

    val compoundExpression = Multiply(Sum(one, two), three)
    println("Eval of compoundExpression i ${eval(compoundExpression)}, should be 9")
}
