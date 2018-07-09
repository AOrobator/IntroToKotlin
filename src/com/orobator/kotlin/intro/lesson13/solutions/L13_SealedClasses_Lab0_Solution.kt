package com.orobator.kotlin.intro.lesson13.solutions

// Create a sealed class called Expr
// It should have two possible subtypes, a Num, that has a single int property,
// and Sum which has two Expr properties, left and right

// Write a method call eval() that takes in an Expr and returns the int value
// of that expression

// Create another subtype of Expr called Mult which has two Expr properties,
// left and right. eval() should be implemented in such a way that this caused
// a compile error. Fix it.

sealed class Expr {
    data class Num(val value: Int) : Expr()
    data class Sum(val left: Expr, val right: Expr) : Expr()
    data class Mult(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int =
        when (e) {
            is Expr.Num -> e.value
            is Expr.Sum -> eval(e.left) + eval(e.right)
            is Expr.Mult -> eval(e.left) * eval(e.right)
        }
