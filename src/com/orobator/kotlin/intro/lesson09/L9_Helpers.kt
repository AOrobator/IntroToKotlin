package com.orobator.kotlin.intro.lesson09

// Fake EditText for demo purposes
class EditText {
    fun addTextChangedListener(watcher: TextWatcher) = Unit
}

interface TextWatcher {
    fun afterTextChanged(text: String)

    fun beforeTextChanged(text: String)

    fun onTextChanged(text: String)
}