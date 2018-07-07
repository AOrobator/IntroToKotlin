package com.orobator.kotlin.intro.lesson09

// Object Declarations
// If we use the object keyword by itself, we create a Singleton
// This object must be named.
// Can't be local (nested in a function)
// Isn't an expression: Can't do `val foo = object UpdateManager { ... }`
// Initialized lazily; when first accessed
object UpdateManager {
    const val version: String = "4.2.0"

    fun checkForUpdates() = Unit // Unit is also an object
}

// Can access them by using the object name
fun main(args: Array<String>) {
    println(UpdateManager.version)

    UpdateManager.checkForUpdates()
}

interface Dispenser {
    fun dispense()
}

open class Beverage(val calories: Int)

// Objects can have super types and implement interfaces
object WaterCooler : Beverage(calories = 0), Dispenser {
    override fun dispense() = println("Blub")
}

// Tools -> Kotlin -> Show bytecode -> Decompile to see what's going on

/*
public final class WaterCooler extends Beverage implements Dispenser {
   public static final WaterCooler INSTANCE;

   public void dispense() {
      String var1 = "Blub";
      System.out.println(var1);
   }

   private WaterCooler() {
      super(0);
   }

   static {
      WaterCooler var0 = new WaterCooler();
      INSTANCE = var0;
   }
}
 */