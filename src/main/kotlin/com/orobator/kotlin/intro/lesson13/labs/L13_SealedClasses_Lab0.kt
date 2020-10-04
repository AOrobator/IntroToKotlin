package com.orobator.kotlin.intro.lesson13.labs

// Create a sealed class called Expression
// It should have two possible subtypes, a Num, that has a single int property,
// and Sum which has two Expression properties, left and right

// Write a method call eval() that takes in an Expression and returns the int
// value of that expression

// Create another subtype of Expression called Multiply which has two Expression
// properties, left and right. eval() should be implemented in such a way that
// this caused a compile error. Fix it.

fun main() {
    // Test Code
//    val one = Expression.Num(value = 1)
//    val sum = Expression.Sum(left = one, right = one)
//    println("Eval of $sum is ${eval(sum)}, should be 2")
//
//    val two = Expression.Num(2)
//    val three = Expression.Num(3)
//    val multiplication = Expression.Multiply(left = two, right = three)
//    println("Eval of $multiplication is ${eval(multiplication)}, should be 6")
//
//    val compoundExpression = Expression.Multiply(Expression.Sum(one, two), three)
//    println("Eval of compoundExpression i ${eval(compoundExpression)}, should be 9")
}