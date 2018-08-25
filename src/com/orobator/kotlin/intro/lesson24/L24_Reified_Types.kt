package com.orobator.kotlin.intro.lesson24

import java.util.concurrent.TimeUnit
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreeNode

// Reified Type Parameters

// Due to type erasure, it's not possible to determine the type of a
// parameter at runtime

// We can get around this using a combination of
// inline functions and reified type parameters
inline fun <reified T> getT() {
    print(T::class)
}

fun main(args: Array<String>) {
    getT<Double>() // prints class kotlin.Double
}

// Inline functions copy the complete function at runtime,
// so parameter types are also once we use reified.

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

// A more Android example with Retrofit
/**
 * Given a baseUrl, and an interface type corresponding
 * to an HTTP API, createApi will return an instance of
 * this interface.
 */
inline fun <reified T : Any> createApi(baseUrl: String): T {
    val client = OkHttpClient.Builder()
            .addInterceptor(customLogger)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

    val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    return retrofit.create(T::class.java)
}

// Call site doesn't need type parameters if they can be inferred.
fun <T> demoCreateApi() {
    val foo = emptyArray<Int>()
    val accountService = createApi<AccountService>("example.com")
    val checkoutService: CheckoutService = createApi("example.com")
    val inventoryService: InventoryService = createApi("example.com")
}

