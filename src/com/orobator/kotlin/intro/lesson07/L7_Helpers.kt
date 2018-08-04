package com.orobator.kotlin.intro.lesson07

import com.orobator.kotlin.intro.lesson22.ShortcutManager

class Context {
    fun getSystemService(any: Any): ShortcutManager = ShortcutManager()
}

class AttributeSet

open class View {
    constructor(context: Context)

    constructor(context: Context, attrs: AttributeSet)
}

object R {
    object layout {
        val custom_view: Int = 0
    }
}