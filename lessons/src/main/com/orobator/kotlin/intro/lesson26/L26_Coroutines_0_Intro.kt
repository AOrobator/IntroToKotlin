package com.orobator.kotlin.intro.lesson26

import com.orobator.kotlin.intro.lesson11.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

////////////////
/* Coroutines */
////////////////

// Coroutines simplify async code by replacing callbacks.

// In an ideal world, you could write all your code synchronously
fun idealWorld() {
    val user: User = getUserFromNetwork(id = 2)
    val favCoffees: List<Coffee> = getFavoriteCoffeesFromNetwork(user)
    displayToUser(favCoffees)
}

// What are some of the reasons we can't do this?
// * We should avoid long running tasks on the main thread.
// * Only the main thread is allowed to interact with views.

// As a result, we need to have a mechanism to transfer data from a
// background thread to the main thread. This brought about the usage of
// callbacks, and Callback Hell right along with it.
fun callbackHell() {
    fetchToken("fake-client-id") { token ->
        fetchUser(token, "fake-user-id") { userString ->
            deserializeUser(userString) { user ->
                showUserData(user)
            }
        }
    }
}

// Many developers tried reactive programming via RxJava
fun theRxWay() {
    val disposable = getToken("fake-client-id")
            .flatMap { getUser(it, "fake-user-id") }
            .flatMap { deserializeUser(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    onSuccess = { showUserData(it) },
                    onError = { handleError(it) })

    // Sometime later
    disposable.dispose()
}


// But many found RxJava to have a steep learning curve with its many operators
// and their return types. Common operations have the ability to become very
// complex.

// Kotlin aims to simplify this by abstracting away the callback pattern.
fun theCoroutineWay() {
    GlobalScope.launch {
        val token = retrieveToken("fake-client-id")
        val userString = retrieveUser(token, "fake-user-id")
        val user = parseUser(userString)
        displayUserData(user)
    }
}

// Special suspend modifier means that this function doesn't block. It suspends
// the execution so the calling thread is free to do other work. This means we
// can call long running suspend functions on the main thread without fear,
// because the main thread won't be blocked.
suspend fun retrieveToken(clientId: String): String {
    delay(250)
    return "token"
}

// Coroutines can replace RxJava Single, Maybe, and Completable.
suspend fun retrieveUser(token: String, userId: String): String {
    delay(250)
    return "Howard"
}

suspend fun parseUser(userString: String): User {
    delay(250)
    return User(name = userString)
}

fun displayUserData(user: User) = Unit

// Coroutines are light-weight!
fun main() = runBlocking {
    repeat(1_000_000) {
        // launch a lot of coroutines
        launch {
            delay(1000L)
            print(".")
        }
    }
}

// This takes a lot longer with threads...