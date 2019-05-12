package com.orobator.kotlin.intro.lesson12.solutions

// Review

// 1. What are some of the differences between Kotlin and Java?

// Kotlin doesn't need semi-colons
// Can leave return types off methods
// vals and vars
// Nullability
// Etc.


// 2. What's the difference between a Java field and a Kotlin property?

// A Java field contains no logic and can be directly assigned and accessed.
// A Java field is also a single entity.
// A Kotlin property is the set of an optional backing field and
// accessor methods (getter/setter)

// 3. Write a function isMultipleOfThree that takes an Int and returns true if
//    it's a multiple of three. False otherwise

fun isMultipleOfThree(n: Int): Boolean = n % 3 == 0

// 4. Create a list of Ints from 1 to 10 called firstList. Create secondList
//    based on firstList. secondList should only have odd numbers. Create
//    thirdList based on secondList. thirdList should be the squares of
//    secondList.

val firstList: List<Int> = List(10) { it + 1 }
val secondList: List<Int> = firstList.filter { it % 2 == 1 }
val thirdList = secondList.map { it * it }