package com.github.shiraji.processor

import com.google.common.truth.Truth.ASSERT
import com.google.testing.compile.JavaSourceSubjectFactory.javaSource
import javax.tools.JavaFileObject

fun compareSourceCodesWithoutError(source: JavaFileObject, expectedSource: JavaFileObject) {
    ASSERT.about(javaSource())
            .that(source)
            .processedWith(KeyEventDispatcherProcessor())
            .compilesWithoutError()
            .and()
            .generatesSources(expectedSource)
}

fun compileShouldFail(source: JavaFileObject) {
    ASSERT.about(javaSource())
            .that(source)
            .processedWith(KeyEventDispatcherProcessor())
            .failsToCompile()
}