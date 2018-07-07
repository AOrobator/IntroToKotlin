package com.orobator.kotlin.intro.lesson8;

import com.orobator.kotlin.intro.lesson7.Context;

public class L8_CompanionObjects_Part2 {
    void accessCompanions() {
        // To access the companion object, we need to directly mention the
        // Companion object. This is very weird and doesn't look like regular Java
        Intent intent = MyActivity.Companion.getIntent(new Context(), 0, 0);

        // Even the constant is declared as a getter function
        String action = MyActivity.Companion.getACTION_FOO();

        // By using @JvmField, tell the compiler not to generate getters and
        // setters and just expose this as a field. This way, we have
        // idiomatic Java
        int jvmField = GoodJavaInterop.JVM_FIELD_VAL;

        // We can achieve a similar result using const val.
        // The main difference is that const vals are compiler time constants
        // so they can be used in annotations.
        // Note: not all vals can be const. The following requirements need to
        // be met:
        // - Top-level or member of an object
        // - Initialized with a value of type String or a primitive type
        // - No custom getter
        int constVal = GoodJavaInterop.CONST_VAL;

        // @JvmStatic gets us a similar effect for methods. It's worth noting
        // that this creates an *additional* static method on the class. The
        // original companion object method still exists.
        GoodJavaInterop.jvmStaticFunction();
        GoodJavaInterop.Companion.jvmStaticFunction();
    }
}
