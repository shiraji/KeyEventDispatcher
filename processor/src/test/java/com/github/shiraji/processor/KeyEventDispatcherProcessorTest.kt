package com.github.shiraji.processor

import com.google.common.base.Joiner
import com.google.testing.compile.JavaFileObjects
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class KeyEventDispatcherProcessorTest : TestCase() {

    @Test fun foo() {
        // @formatter:off
        val source = JavaFileObjects.forSourceString("test.Foo", Joiner.on('\n').join(
                "package test;",
                "import com.github.shiraji.keyevent.annotations.KeyEvent;",
                "@KeyEvent",
                "public class Foo {",
                "}"
        ))

        val expectedSource = JavaFileObjects.forSourceString("test.KenKenPa_SimpleFSM", Joiner.on('\n').join(
                "package test;",
                "public final class Foo\$KeyEvent {",
                "}"
        ))
        // @formatter:on

        compareSourceCodesWithoutError(source, expectedSource)
    }

}