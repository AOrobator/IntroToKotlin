package com.orobator.kotlin.intro.lesson13.example.viewstate

// Sealed classes can also be used to represent view state
sealed class CheckoutViewState {
    object Loading : CheckoutViewState()
    data class CheckoutSuccess(val orderSummary: String) : CheckoutViewState()
    data class CheckoutFailed(val error: Throwable) : CheckoutViewState()
}

fun render(viewState: CheckoutViewState) = when (viewState) {
    CheckoutViewState.Loading -> println("Loading")
    is CheckoutViewState.CheckoutSuccess -> println(viewState.orderSummary)
    is CheckoutViewState.CheckoutFailed -> viewState.error.printStackTrace()
}

