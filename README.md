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

Next, import the IntroToKotlin project into IntelliJ. When prompted, choose 
create project from existing sources.

![Import project][import_project]

Next, make sure that the project location is properly specified and the project 
format is .idea.

![Import project location][import_project_location] 

Click next several times until you're at a screen that looks like this:

![Project SDK][project_sdk]

Make sure to select Java 1.8 as the Project SDK, then hit next until you finish.

Once you have the project imported, you may be prompted to import the project as
a Gradle project.

![Import gradle project][import_gradle_project]

After clicking the prompt, select "Create separate module per source set" and 
"Use default Gradle wrapper", then hit OK.

![Import module from gradle][import_module_from_gradle]

Finally, right click on lessons/src/main and mark the directory as a Sources 
Root.

![mark_as_sources_root][mark_as_sources_root]

You should now be able to run L0_HelloWorld.kt
via a green play button in the gutter.

![Run code][run_code]

If you see a configure Kotlin popup window, feel free to dismiss it, as Kotlin 
should already be configured.

[Git]: https://git-scm.com/downloads
[kotlin_banner]: img/kotlin_banner.png "Kotlin Logo"
[Kotlin]: https://kotlinlang.org/
[Java 1.8]: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[IntelliJ]: https://www.jetbrains.com/idea/download/
[import_project]: img/import_project.png "Import Project"
[import_project_location]: img/import_project2.png "Import Project, Location"
[project_sdk]: img/project_sdk.png "Project SDK"
[import_gradle_project]: img/import_gradle_project.png "Import Gradle project"
[import_module_from_gradle]: img/import_module_from_gradle.png "Import module from gradle"
[mark_as_sources_root]: img/mark_directory_as_sources_root.png "Mark as sources"
[run_code]: img/run_code.png "Run Code"