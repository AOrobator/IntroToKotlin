package com.orobator.kotlin.intro.lesson16

object sharedPreferences {
    fun edit(): sharedPreferences = this
    fun edit(editAction: sharedPreferences.() -> sharedPreferences) = this.editAction()
    fun putBoolean(s: String, b: Boolean) = this
    fun apply() = Unit
}