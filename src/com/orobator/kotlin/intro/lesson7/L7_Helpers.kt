package com.orobator.kotlin.intro.lesson7

class Context
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