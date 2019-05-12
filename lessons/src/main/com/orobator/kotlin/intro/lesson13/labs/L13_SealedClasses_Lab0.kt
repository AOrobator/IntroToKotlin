package com.orobator.kotlin.intro.lesson13.labs

// Create a sealed class called Expr
// It should have two possible subtypes, a Num, that has a single int property,
// and Sum which has two Expr properties, left and right

// Write a method call eval() that takes in an Expr and returns the int value
// of that expression

// Create another subtype of Expr called Mult which has two Expr properties,
// left and right. eval() should be implemented in such a way that this caused
// a compile error. Fix it.