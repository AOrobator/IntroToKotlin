package com.orobator.kotlin.intro.lesson26

import com.orobator.kotlin.intro.lesson11.User

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