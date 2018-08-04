package com.orobator.kotlin.intro.lesson22

class Button
class ClickEvent
data class ShortcutInfo(val id: String)
class ShortcutManager {
    fun requestPinShortcut(shortcut: ShortcutInfo) = Unit
}

object Build {
    object VERSION {
        var SDK_INT = 0
    }

    object VERSION_CODES {
        const val N_MR1 = 0
    }
}