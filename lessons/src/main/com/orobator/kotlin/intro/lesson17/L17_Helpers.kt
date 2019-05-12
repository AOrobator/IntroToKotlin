package com.orobator.kotlin.intro.lesson17

class Bundle {
    fun putString(name: String, defValue: String?) = Unit
    fun getString(name: String) = ""
}

class Dialog

class AlertDialog {
    class Builder(any: Any) {
        fun setTitle(s: String?): Builder = this
        fun setMessage(s: String?): Builder = this
        fun setPositiveButton(s: String, f: (Any, Any) -> Unit): Builder = this
        fun create(): Dialog = Dialog()
    }
}

abstract class DialogFragment {
    val activity: Any? = Any()
    var arguments: Bundle? = Bundle()
    abstract fun onCreateDialog(savedInstanceState: Bundle?): Dialog
}