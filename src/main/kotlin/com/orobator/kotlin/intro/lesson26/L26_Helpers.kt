package com.orobator.kotlin.intro.lesson26

import com.google.gson.annotations.SerializedName
import com.orobator.kotlin.intro.lesson11.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Ideal World
class Coffee
fun getUserFromNetwork(id: Int): User = stub()
fun getFavoriteCoffeesFromNetwork(user: User): List<Coffee> = stub()
fun displayToUser(favCoffees: List<Any>) = Unit
fun stub(): Nothing = throw NotImplementedError()

// Callback Hell
fun fetchToken(clientId: String, callback: (Any) -> Unit) = Unit
fun fetchUser(token: Any, userId: String, callback: (Any) -> Unit) = Unit
fun deserializeUser(user: Any, callback: (Any) -> Unit = {}) = Unit
fun showUserData(user: Any) = Unit

// The Rx Way
class Scheduler
object AndroidSchedulers { fun mainThread() = Scheduler() }
object Schedulers { fun io() = Scheduler() }
object FakeRx {
    fun flatMap(mapper: (Any) -> Any) = FakeRx
    fun subscribeOn(scheduler: Scheduler) = FakeRx
    fun observeOn(scheduler: Scheduler) = FakeRx
    fun subscribe(onSuccess: (Any) -> Unit, onError: (Throwable) -> Unit) = FakeRx
    fun dispose() = Unit
}
fun handleError(throwable: Throwable) = Unit
fun getUser(token: Any, s: String) = Unit
fun getToken(s: String) = FakeRx

// Structured Concurrency
data class NumberFact internal constructor(
    @SerializedName("text") val text: String,
    @SerializedName("number") val number: Double,
    @SerializedName("found") val found: Boolean,
    @SerializedName("type") val type: String
)

interface NumbersApi {
    @GET("/random/math?json")
    suspend fun getRandomMathFact(): NumberFact

    @GET("/random/trivia?json")
    suspend fun getRandomNumberFact(): NumberFact

    companion object {
        operator fun invoke(): NumbersApi {
            return Retrofit.Builder()
                .baseUrl("http://numbersapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NumbersApi::class.java)
        }
    }
}
