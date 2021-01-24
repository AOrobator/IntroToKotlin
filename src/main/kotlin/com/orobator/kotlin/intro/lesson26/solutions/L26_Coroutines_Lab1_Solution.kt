package com.orobator.kotlin.intro.lesson26.solutions

import kotlinx.coroutines.*

// Lab 1: Coroutine Builders

// Q: What are coroutine builders used for? (Don't say building coroutines lol)

// A suspending function can only be called from another suspending function or
// a coroutine. In order to bridge the gap between non-suspending functions and
// suspending functions, we can use a coroutine builder such as launch where the
// body of the lambda passed in is a coroutine that can call suspending
// functions.


// Q: Given the AccountsApi, print out the checkings and savings balances on one
// line with the format:

// Checkings Balance = A, Savings Balance = B

// In displayBalancesSerially() you should make sure that the fetch to get the
// checkings balance completes before the call to get the savings balance is
// made.

// In displayBalancesConcurrently() you should make sure that the calls to get
// the checkings and savings balances are running at the same time. Your code
// should make sure that both calls have completed before printing the result.

// Both functions should have identical output of:
// Checkings Balance = $420, Savings balance = $720

object AccountsApi {
    suspend fun getCheckingsBalance(): String {
        delay(1000L)
        return "$420"
    }

    suspend fun getSavingsBalance(): String {
        delay(1000L)
        return "$720"
    }
}

fun main() {
    // This can be solved without modifying main()
    displayBalancesSerially()
    displayBalancesConcurrently()
}

fun displayBalancesSerially() = runBlocking {
    val checkings = AccountsApi.getCheckingsBalance()
    val savings = AccountsApi.getSavingsBalance()

    println("Checkings Balance = $checkings, Savings balance = $savings")
}

fun displayBalancesConcurrently() = runBlocking {
    val checkings = async { AccountsApi.getCheckingsBalance() }
    val savings = async { AccountsApi.getSavingsBalance() }

    println("Checkings Balance = ${checkings.await()}, Savings balance = ${savings.await()}")
}

// Launch example - maybe firing off an analytics event?