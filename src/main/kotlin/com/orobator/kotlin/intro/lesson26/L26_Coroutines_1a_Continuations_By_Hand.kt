package com.orobator.kotlin.intro.lesson26

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

// If you're using an API you didn't write that uses callbacks, you
// can manually add your own continuation and use suspending functions
suspend fun Call.await(): ResponseBody = suspendCoroutine { cont ->
    enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                cont.resume(response.body()!!)
            } else {
                cont.resumeWithException(ErrorResponse(response))
            }
        }

        override fun onFailure(call: Call, e: IOException) {
            cont.resumeWithException(e)
        }
    })
}

data class ErrorResponse(val response: Response): Exception()