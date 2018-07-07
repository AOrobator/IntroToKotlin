package com.orobator.kotlin.intro.lesson02.solutions

fun main(args: Array<String>) {
    // Write a single line function called takeOdds that takes in a generic
    // list and returns a list of all the items at an odd index.
    fun <T> takeOdds(list: List<T>): List<T> = list.filterIndexed { index, _ -> index % 2 == 1 }

    println(takeOdds(listOf(1, 2, 3, 4)))
}