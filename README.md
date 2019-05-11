# Intro to Kotlin

![Kotlin Banner][kotlin_banner]

This project is an introduction to the [Kotlin] programming language. It covers 
many language features from nullable properties to extension functions and Java
interoperability.

## Getting Started

This course requires the following to get started:

* [Git]
* [Java 1.8]
* [IntelliJ] Community Edition 2019.1.2 or later

Clone the repo into your desired location like so:

```
git clone https://github.com/AOrobator/IntroToKotlin.git
```

Next, open the IntroToKotlin folder in IntelliJ.

Once you have the project imported, you'll have to do some project configuration. 

First, open the Project Structure view.

![Opening project structure][opening_project_structure]

Under the Project section, make sure to set your project SDK to Java 1.8 and set
your project language level to 8. Then set your Project compiler output to 
IntroToKotlin/build

![Project settings][project_settings]

Under the Modules section, select the `src` folder and mark it as a Sources 
directory.

![Mark as source directory][mark_as_source]

Under the Libraries section, click the plus button in the top left and select 
Java from the New Project Library dialog. Then select 
lib/annotations-17.0.0.jar.

![Project structure libraries][project_structure_libs]

Click OK and close the Project Structure dialog. In the code editor, you may be 
prompted to configure Kotlin.

![Configure Kotlin][configure_kotlin]

Choose Java and restart IntelliJ. You should now be able to run L0_HelloWorld.kt
via a green play button in the gutter.

![Run code][run_code]

[Git]: https://git-scm.com/downloads
[kotlin_banner]: img/kotlin_banner.png "Kotlin Logo"
[Kotlin]: https://kotlinlang.org/
[Java 1.8]: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[IntelliJ]: https://www.jetbrains.com/idea/download/
[opening_project_structure]: img/opening_project_structure.png "Opening Project Structure"
[project_settings]: img/project_structure_project.png "Project Settings"
[mark_as_source]: img/mark_as_source.png "Mark as source"
[project_structure_libs]: img/project_structure_libraries.png "Libraries"
[configure_kotlin]: img/configure_kotlin.png "Configure Kotlin"
[run_code]: img/run_code.png "Run Code"