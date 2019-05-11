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