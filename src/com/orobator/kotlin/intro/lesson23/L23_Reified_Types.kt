package com.orobator.kotlin.intro.lesson23

import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreeNode

// Reified Type Parameters

// Sometimes we need to access a type passed to us as a parameter:

fun <T> TreeNode.findParentOfType(clazz: Class<T>): T? {
    var p = parent
    while (p != null && !clazz.isInstance(p)) {
        p = p.parent
    }
    @Suppress("UNCHECKED_CAST")
    return p as T?
}

// Here, we walk up a tree and use reflection to check if a node has
// a certain type. It works, but the call site is not very pretty:

fun demoCallSite() {
    val treeNode = DefaultMutableTreeNode()
    treeNode.findParentOfType(MyTreeNode::class.java)
}

// What we actually want is simply pass a type to this function,
// i.e. call it like this:

const val idealCallSite = "treeNode.findParentOfType<MyTreeNode>()"

// To enable this, we'll do 2 things.
// First, we'll inline the function

inline fun <T> TreeNode.inlineFindParentOfType(clazz: Class<T>): T? {
    var p = parent
    while (p != null && !clazz.isInstance(p)) {
        p = p.parent
    }
    @Suppress("UNCHECKED_CAST")
    return p as T?
}

// When we inline a function, every time the function is called, instead of the
// function being invoked, the code body of the function is pasted in.

// This causes generated code to grow