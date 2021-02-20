package com.orobator.kotlin.intro.lesson26

import java.lang.Thread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.cancel
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.plus


// Structured concurrency
// See https://github.com/Kotlin/kotlinx.coroutines/blob/master/docs/basics.md
// for more.

fun main() = runBlocking {
    jobJoinDemo()
    structuredConcurrencyDemo()

    val viewModel = StockTickerViewModel(this)
    viewModel.init()
    delay(15_000)
    viewModel.onDestroy()
    joinAll()
    println("Complete!")
}

/**
 * After launching a coroutine, you may want to wait for it to complete before
 * continuing program execution. If you were using threads, you could use
 * [Thread.join] in order to wait for a particular thread to die. You can do
 * something similar with coroutines and [Job]s.
 */

fun jobJoinDemo() = runBlocking {
    val job: Job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join() // wait until child coroutine completes
}

// Notice that the launch function returns a Job. You can think of a Job as
// being the handle to your coroutine's ongoing work. With a reference to your
// coroutine's job, you can either wait for it to finish with the join method,
// or you can cancel the job to stop the execution of your coroutine.

// Structured concurrency

// There is still something to be desired for practical usage of coroutines.
// When we use GlobalScope.launch, we create a top-level coroutine. Even though
// it is light-weight, it still consumes some memory resources while it runs. If
// we forget to keep a reference to the newly launched coroutine, it still runs.
// What if the code in the coroutine hangs (for example, we erroneously delay
// for too long), what if we launched too many coroutines and ran out of memory?
// Having to manually keep references to all the launched coroutines and join
// them is error-prone.
//
// There is a better solution. We can use structured concurrency in our code.
// Instead of launching coroutines in the GlobalScope, just like we usually do
// with threads (threads are always global), we can launch coroutines in the
// specific scope of the operation we are performing.
//
// In our example, we have a main function that is turned into a coroutine using
// the runBlocking coroutine builder. Every coroutine builder, including
// runBlocking, adds an instance of CoroutineScope to the scope of its code
// block. We can launch coroutines in this scope without having to join them
// explicitly, because an outer coroutine (runBlocking in our example) does not
// complete until all the coroutines launched in its scope complete. Thus, we
// can make our example simpler:

fun structuredConcurrencyDemo() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine in the scope of runBlocking
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}

// A coroutine is always launched within a CoroutineScope. Coroutine builders
// like launch and runBlocking give our coroutines a scope to run in.

// Structured concurrency is very useful because we don't have to call Job.join
// all the time, but it also helps us out with cancellation.

// Let's pretend that we're writing a ViewModel that is responsible for the
// business logic of a particular screen. In this example, we'll write
// StockTickerViewModel which makes a network call to refresh the stock tickers
// on screen every 5 seconds. We'll also be updating the price of Bitcoin every
// 10 seconds. -> ELABORATE w/ ViewModelScope, lifecycle example

data class NumberFact internal constructor(
    @field:SerializedName("text") val text: String,
    @field:SerializedName("number") val number: Double,
    @field:SerializedName("found") val found: Boolean,
    @field:SerializedName("type") val type: String
)

interface NumbersApi {
    @GET("/random/math?json")
    suspend fun getRandomMathFact(): NumberFact

    @GET("/random/trivia?json")
    suspend fun getRandomNumberFact(): NumberFact
}

class StockTickerViewModel(coroutineScope: CoroutineScope) {
    // First we'll create our own CoroutineScope that we'll use to launch all of
    // our coroutines in. The ViewModel provided in the Android Jetpack
    // libraries provides us with a viewModelScope with a similar
    // implementation.
    private val viewModelScope: CoroutineScope = coroutineScope + SupervisorJob()
    private var isRunning = true

    fun onDestroy() {
        isRunning = false
        viewModelScope.cancel()
    }

    // When we go to the definition of MainScope, it looks like it's made out of
    // 3 components: ContextScope, SupervisorJob, and Dispatchers.Main. Let's
    // focus on SupervisorJob for now. Children of a supervisor job can fail
    // independently of each other. A failure or cancellation of a child does
    // not cause the supervisor job to fail and does not affect its other
    // children.

    fun init() {
        val numbersApi: NumbersApi = Retrofit.Builder()
            .baseUrl("http://numbersapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NumbersApi::class.java)

        // Because children can fail independently, EXPLAIN ONE WON"T CRASH THE OTHER
        viewModelScope.launch {
            while (isRunning) {
                val mathFact = numbersApi.getRandomMathFact()
                println("Math Fact: $mathFact}\n")
                delay(3_000L)

                if (System.currentTimeMillis() % 2 == 0L) {
                    throw IllegalStateException("Fail math fact")
                }
            }
        }

        viewModelScope.launch {
            while (isRunning) {
                val numberFact = numbersApi.getRandomNumberFact()
                println("Number Fact: $numberFact\n")
                delay(2_000L)
                // Fetch BTC
            }
        }
    }
}


// - Close scope of viewModel, in between it launches a bunch of coroutines

val foo = Thread().join()
// Compare to thread.join(), tracking all launched threads and having to manage them

// Instead use scopes. coroutine builders (examples) give your coroutines a scope to run in

// lambda with receiver type as parameter to coroutine builder

// mention GlobalScope

// show code with running coroutine getting cancelled? throw in some scopes?

///////////////
// Benefits

// The scope is generally responsible for child coroutines, and their lifetime
// is attached to the lifetime of the scope.

// The scope can automatically cancel child coroutines if something goes wrong
// or if a user simply changes their mind and decides to revoke the operation.

// The scope automatically waits for completion of all the child coroutines.
// Therefore, if the scope corresponds to a coroutine, then the parent coroutine
// does not complete until all the coroutines launched in its scope are complete.


// Compare cancellation of GlobalScope with cancellation of other scopes

//////////
// Jobs

// coroutine builders usually return jobs (runBlocking is an exception)
// job.join()

// Cancellation,
// lab exploring cancellation
// Scoping (SupervisorScope)
