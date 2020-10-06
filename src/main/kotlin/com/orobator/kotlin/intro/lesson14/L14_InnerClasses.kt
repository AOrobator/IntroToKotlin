package com.orobator.kotlin.intro.lesson14

// Inner Classes

class WishlistAdapter {
    val listItems = listOf("Shoes", "Belt", "Tie")

    // Classes can be nested in other classes
    class WishlistItemViewHolder {
        // Nested classes can't access the outer class's members by default
        // Can't access listItems, opposite of Java
    }

    // A class may be marked as inner to be able to access members of
    // outer class. Inner classes hold onto a reference of the outer class
    inner class ItemViewHolder {
        init {
            // Can qualify reference to "this" with @ClassName.
            println(this@WishlistAdapter.listItems.size)
        }
    }

    // Not recommended for use: Implicit dependencies can make leaks harder to
    // track down if the inner class has a longer lifecycle than the outer
    // class. Instead, pass in dependencies explicitly.
}