package com.orobator.kotlin.intro.lesson19

// Generics

// Classes in Kotlin may have type parameters
// Type parameter(s) of class goes after class name
class Box<T>(t: T) {
    var value = t
}

// In general, we need to provide type arguments when instantiating

val box: Box<Int> = Box<Int>(1)

// If parameters can be inferred, e.g. from the constructor args or some other
// means, one can omit the type arguments

// 2 has type Int,
// so the compiler figures out that we are talking about Box<Int>
val box2 = Box(2)

// Variance

// Instead of wildcard types, Kotlin has declaration-site variance
// and type projections.

// Generic types in Java are invariant
// - List<String> is NOT a subtype of List<Object>

// Any object can go in a List<Object>, but only Strings can go in List<String>
// List<String> has less functionality than List<Object>

// If List wasn't invariant, the following code would be possible

val illegalJava = """
  // Java
  List<String> strs = new ArrayList<String>();
  // The cause of the upcoming problem lies below
  List<Object> objs = strs; // Java prohibits this!
  objs.add(1); // Here we put an Integer into a list of Strings
  String s = strs.get(0); // ClassCastException: Can't cast Integer to String
"""

// Java prohibits this to ensure run-time safety, but this has implications
// For example, Collection#addAll. Intuitively the signature would be:

val naiveAddAll = """
  // Java
  interface Collection<E> ... {
    void addAll(Collection<E> items);
  }
"""

// But that would prevent the following safe operation:

val safeOperation = """
  // Java
  void copyAll(Collection<Object> to, Collection<String> from) {
    to.addAll(from);
    // !!! Would not compile with the naive declaration of addAll:
    // Collection<String> is not a subtype of Collection<Object>
  }
"""

// Which is why the actual signature of addAll is the following:

val realAddAll = """
  // Java
  interface Collection<E> ... {
    void addAll(Collection<? extends E> items);
  }
"""

// The wildcard type argument "? extends E" says this method takes a collection
// of objects with type E, or some subtype of E.

// We can safely *read* E's from items, but can't write to it.
// We don't know what objects comply with the unknown subtype of E.

// Now we have desired behavior:
// Collection<String> is a subtype of Collection<? extends Object>

// The wildcard with an extends-bound (upper bound) makes the type covariant.
// Think of Object as being at the top of the type hierarchy

// If you only TAKE items from a collection, then using a collection of Strings
// and reading Objects from it is fine.

// Conversely if you only PUT items into the collection, it's okay to take a
// collection of Objects and put Strings into it.

// In Java, we'd have List<? super String> which is a supertype of List<Object>
// This is called contravariance. You can only call methods that take String as
// an argument on List<? super String> (e.g. add(String) or set(int, String))

// Calling a method that returns T in List<T> returns an Object not a String

// Objects you read from - Producers
// Objects you write to - Consumers
// PECS - Producer-Extends, Consumer-Super

// Declaration-Site variance

// Let's say we have a generic interface Source<T> that only has methods that
// return T
val genericInterface = """
  // Java
  interface Source<T> {
    T nextT();
  }
"""

// It's fine to store a reference of Source<String> in a variable of type
// Source<Object> - no consumer methods, but Java still prohibits this.

val illegalUsage = """
  // Java
  void demo(Source<String> strs) {
    Source<Object> objects = strs; // !!! Not allowed in Java
    // ...
  }
"""

// Fixing this requires us to declare objects as type Source<? extends Object>
// Sort of meaningless, because we can call all the same methods as before.
// No value added by complex type.

// In Kotlin, we can explain this type of thing to the compiler
// It's called declaration-site variance.

// We can annotate the type parameter of T of source to make sure that it is
// only returned (produced) from members of Source<T>, never consumed.

// We do this by using the `out` modifier

interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter
    // ...
}

// General rule: When a type parameter T of class C is declared out, it may
// occur only in out-position members of C. In return C<Base> can safely be a
// supertype of C<Derived>

// C is covariant in the parameter T, or T is a covariant type parameter.
// You can think of C as being a *producer* of T's and NOT a consumer of T's.

// `out` modifier is called a variance annotation. Since it's provided at the
// type parameter declaration site, it's declaration-site variance.

// Compare this to Java's use-site variance where wildcards in the type usages
// make the types covariant.

// In addition to `out`, Kotlin provides a complementary variance annotation
// `in`. It makes a type contravariant: can only be consumed, not produced.
// A good contravariant type example is Comparable:

interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, we can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
}

// Consumer in, Producer out