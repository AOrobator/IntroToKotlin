package com.orobator.kotlin.intro

/**
 * @param args Command Line arguments
 */
fun main(args: Array<String>) {
    val scope = "World"
    println("Hello $scope!") // Kotlin supports String interpolation

    ////////////////
    /* val vs var */
    ////////////////

    val name = "Andrew" // read-only variable
    var age = 24 // read & write variable

    age = 25 // Allowed, because this is a var
    // name = "Dan" // Not allowed because this is a val


    ////////////////////
    /* Type Inference */
    ////////////////////

    val pet: String = "Husky" // Can optionally specify the type
    val food = "Tomato" // This is fine as well. The compiler knows this is a String


    //////////////////////////////
    /* Basic Types: Nullability */
    //////////////////////////////

    var nullableFruit: String? = "Pomegranate"
    nullableFruit = null

    var nonNullFruit: String = "Grape"
    nonNullFruit = "Apple" // Okay because it isn't null
    // nonNullFruit = null // Compiler Error

    // Safe call operator, avoids NullPointerException
    val nullableLength: Int? = nullableFruit?.length
    println("Nullable Length: $nullableLength")

    try {
        val nullLength: Int = nullableFruit!!.length
    } catch (e: NullPointerException) {
        println("Nullable fruit was null")
    }

    // Setting a variable to an expression. Also works with try/catch
    val nonNullLength: Int = if (nullableFruit == null) 0 else nullableFruit.length

    // Kotlin's Elvis operator is short form for the above
    val nonNullLength2: Int = nullableFruit?.length ?: 0

    println("nonNullLength2 = $nonNullLength2")


    //////////////////////////
    /* Basic Types: Numbers */
    //////////////////////////

    val myDouble: Double = 42.0 // 64 bits wide
    val myFloat: Float = 42f // 32 bits wide

    val myLong: Long = 42L // 64 bits wide
    val myInt: Int = 42 // 32 bits wide
    val myShort: Short = 42 // 16 bits wide
    val myByte: Byte = 42 // 8 bits wide

    val hexadecimalInt: Int = 0x0F
    val binaryLong: Long = 0b101
    val oneMillion = 1_000_000

    // Everything is an object!
    // Char is not a Number

    // Smaller types not subtypes of larger types, due to different representations.
    // val impossible: Long = myInt
    val castedLong: Long = myInt.toLong()
    println("Casted Long = $castedLong")

    // Numbers are represented as primitives unless generics or nullability is involved.
    // Boxing doesn't necessarily preserve identity

    val a: Int = 10000
    println("a identical to itself? ${a === a}") // Prints 'true'
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println("boxedA identical to anotherBoxedA? ${boxedA === anotherBoxedA}") // !!!Prints 'false'!!!

    // However, it does preserve equality
    println("boxedA equal to anotherBoxedA? ${boxedA == anotherBoxedA}") // Print true

    // Every number type supports casting to other number types and Char
    1.toByte()
    1.toShort()
    1L.toInt()
    1.toLong()
    1.toFloat()
    1.toDouble()
    1.toChar()

    // Arithmetic Operations: All numbers support basic operations
    val arithmetic = 1 + 2 * 3 - 4 % 5

    // Bitwise Operations: Named functions (Int and Long only)
    val bitwiseOps = ((1 and 2) or (3 xor 4)) shr 5

    // Equality Checks
    val equalityCheck0 = 1 == 2
    val equalityCheck1 = 1 != 2

    // Comparison Operators
    val compLessThan = 1 < 2
    val compLessThanOrEqualTo = 1 <= 2
    val compGreaterThan = 1 > 2
    val compGreaterThanOrEqualTO = 1 >= 2

    // Ranges
    println("2 in range of 1..4: ${2 in 1..4}")
    println("4 in range of 1..4: ${4 in 1..4}")
    println("4 in range of 1 until 4: ${4 in 1 until 4}") // end not inclusive
    println("5 not in range of 1..4: ${5 !in 1..4}")

    for (i in 0..2) println("Going up, iteration $i")

    for (i in 2 downTo 0) println("Going down, iteration $i")

    for (i in 0..4 step 2) println("Skipping steps, iteration $i")


    //////////////////////////
    /* Basic Types: Boolean */
    //////////////////////////

    val myTrueBool: Boolean = true
    val myFalseBool: Boolean = false

    // Booleans are boxed if a nullable reference is needed.

    println("Kotlin is cool: ${(myTrueBool || myFalseBool) && !myFalseBool}")


    ///////////////////////
    /* Basic Types: Char */
    ///////////////////////

    // Char can't be treated as a number

    val myChar: Char = 'a' // Literals go in single quotes

    // Can be used in ranges
    println("myChar in 'a'..'z': ${myChar in 'a'..'z'}")


    //////////////////////////
    /* Basic Types: Strings */
    //////////////////////////

    // Can iterate over characters
    for (c in "Kotlin Rocks!") {
        print(c)
    }
    println()

    // String concatenation
    val concatString0 = "Two " + "strings"
    println("concatString0: $concatString0")

    val concatString1 = "String and Int" + 42
    println("concatString1: $concatString1")

    val stringTemplate = "Template ${14 + 28}"
    println("stringTemplate: $stringTemplate")
    println("stringTemplate is ${stringTemplate.length} chars long")

    val rawString = """
        for (c in "foo")
            print(c)

        // Looks like a comment, but is still in the string.
        """

    println("Raw String:\n $rawString")

    val withoutMargins = """ABC
                           |123
                           |456
         """.trimMargin() // prints ABC\n123\n456

    println("Without margins:\n$withoutMargins")

    val testStringIdentity1 = "Foo"
    val testStringIdentity2 = testStringIdentity1[0] + "oo" // Can index strings
    println("Strings are equal? ${testStringIdentity1 == testStringIdentity2}")
    println("Strings are identical? ${testStringIdentity1 === testStringIdentity2}")

    // Tons of String extension functions - https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html

    val blankString: String? = "\t\t\t\t"
    println("Is blankString blank? ${blankString.isNullOrBlank()}")
    println("Ignoring case, are these equal? ${"Rabbit".equals("rAbBiT", ignoreCase = true)}")


    ////////////
    /* Arrays */
    ////////////

    val myStringArray: Array<String> = arrayOf("foo", "bar")
    val myNullStringArray: Array<String?> = arrayOfNulls(3)

    // Creates an Array<String> with values ["0", "1", "4", "9", "16"]
    val squares = Array(5, { i -> (i * i).toString() })

    println("Size of squares = ${squares.size}")
    println("squares[3] = ${squares[3]}")

    // Specialized classes for primitive types to avoid boxing
    // No inheritance relation to Array, but has same methods & properties

    val myIntArray: IntArray = intArrayOf(1, 2, 3)
    myIntArray[0] = myIntArray[1] + myIntArray[2]
}