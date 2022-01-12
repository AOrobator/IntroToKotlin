# Intro to Kotlin

![Kotlin Banner][kotlin_banner]

This project is an introduction to the [Kotlin] programming language. It covers 
many language features from nullable properties to extension functions and Java
interoperability.

# Table of Contents
1. [Getting Started](#getting_started)
2. [Project Structure](#project_structure)
3. [Lessons](#lessons)

## Getting Started <a name="getting_started"/>

This course requires the following to get started:

* [Git]
* [Java 1.8]
* [IntelliJ] Community Edition 2020.2 or later

Clone the repo into your desired location like so:

```
git clone https://github.com/AOrobator/IntroToKotlin.git
```

Next, open the IntroToKotlin project with IntelliJ.

![open_project][open_project]

You should now be able to run L0_HelloWorld.kt
via a green play button in the gutter.

![Run code][run_code]

## Project Structure <a name="project_structure"/>

- This is a Kotlin only project that is broken into several packages. Each package goes over a specific concept in 
  Kotlin. Even though this is a Kotlin only project, some Android concepts are covered.
- Most lessons have a main lesson file that goes over the concept being taught. Then there are labs that test 
  understanding of each concept. Solutions are located on the master branch, but make sure to fully attempt each 
  question before looking at the solutions.

## Lessons <a name="lessons">
- Lesson 00 - Hello World, basic types
- Lesson 01 - Collections
- Lesson 02 - Functions
- Lesson 03 - Control Flow
- Lesson 04 - Classes Intro
- Lesson 05 - Properties
- Lesson 06 - Visibility
- Lesson 07 - Inheritance
- Lesson 08 - Companion Objects
- Lesson 09 - Objects
- Lesson 10 - Interfaces
- Lesson 11 - Data Classes
- Lesson 12 - Enum
- Lesson 13 - Sealed Classes
- Lesson 14 - Inner Classes
- Lesson 15 - Exceptions
- Lesson 16 - Extensions
- Lesson 17 - Delegates
- Lesson 18 - Operator Overloading
- Lesson 19 - Generics Declaration Site Variance
- Lesson 20 - Generics Type Projections
- Lesson 21 - Generic Functions
- Lesson 22 - Higher Order Functions
- Lesson 23 - Inline Functions
- Lesson 24 - Reified Types
- Lesson 25 - Kotlin/Java Interop
- Lesson 26 - Coroutines

[Git]: https://git-scm.com/downloads
[kotlin_banner]: img/kotlin_banner.png "Kotlin Logo"
[Kotlin]: https://kotlinlang.org/
[Java 1.8]: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[IntelliJ]: https://www.jetbrains.com/idea/download/
[open_project]: img/open_project.png "Import Project"
[run_code]: img/run_code.png "Run Code"