package com.orobator.kotlin.intro.lesson24

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
// We'll inline the function and add a reified type parameter

inline fun <reified T> TreeNode.findParentOfType(): T? {
    var p = parent
    while (p != null && p !is T) {
        p = p.parent
    }
    return p as T?
}

fun improvedCallSite() {
    val treeNode = DefaultMutableTreeNode()
    treeNode.findParentOfType<MyTreeNode>()
}
