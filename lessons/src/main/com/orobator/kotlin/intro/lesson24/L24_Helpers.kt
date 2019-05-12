package com.orobator.kotlin.intro.lesson24

import java.util.concurrent.TimeUnit

interface MyTreeNode
object OkHttpClient {
    class Builder {
        fun addInterceptor(logger: Any) = this
        fun connectTimeout(i: Int, seconds: TimeUnit) = this
        fun readTimeout(i: Int, seconds: TimeUnit) = this
        fun build(): Any = this
    }
}

object Retrofit {
    fun <T> create(clazz: Class<T>): T = TODO()

    class Builder {
        fun baseUrl(baseUrl: String) = this
        fun addConverterFactory(any: Any) = this
        fun client(client: Any) = this
        fun build() = Retrofit
    }
}

object GsonConverterFactory {
    fun create() = Unit
}

val customLogger = Any()

interface AccountService
interface CheckoutService
interface InventoryService