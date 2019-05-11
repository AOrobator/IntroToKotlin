package com.orobator.kotlin.intro.lesson26

import com.orobator.kotlin.intro.lesson11.User

// Ideal World
class Coffee
fun getUserFromNetwork(id: Int): User = stub()
fun getFavoriteCoffeesFromNetwork(user: User): List<Coffee> = stub()
fun displayToUser(favCoffees: List<Any>) = Unit
fun stub(): Nothing = throw NotImplementedError()

// Callback Hell
fun fetchToken(clientId: String, callback: (Any) -> Unit) = Unit
fun fetchUser(token: Any, userId: String, callback: (Any) -> Unit) = Unit
fun deserializeUser(user: Any, callback: (Any) -> Unit) = Unit
fun showUserData(user: Any) = Unit