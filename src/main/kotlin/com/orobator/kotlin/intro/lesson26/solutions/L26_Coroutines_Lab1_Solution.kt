package com.orobator.kotlin.intro.lesson26.solutions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Lab on builders

// have them use async to fetch 2 things from fake network concurrently.
fun concurrentFetch() = GlobalScope.launch {

}

suspend fun CoroutineScope.concurrentFetch(userId1: String, userId2: String) {

}

// Launch example - maybe firing off an analytics event?

// Run blocking example
