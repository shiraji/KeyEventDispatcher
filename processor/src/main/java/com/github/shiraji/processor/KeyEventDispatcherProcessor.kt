package com.github.shiraji.processor

import com.github.shiraji.keyevent.annotations.KeyEvent
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Filer
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

class KeyEventDispatcherProcessor : AbstractProcessor() {

    private lateinit var elementUtils: Elements
    private lateinit var typeUtils: Types
    private lateinit var filer: Filer

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)

        processingEnv?.let {
            elementUtils = it.elementUtils
            typeUtils = it.typeUtils
            filer = it.filer
        }
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return hashSetOf(KeyEvent::class.java.canonicalName)
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        roundEnv?.getElementsAnnotatedWith(KeyEvent::class.java)?.forEach {
            val builder = TypeSpec
                    .classBuilder("${it.simpleName}\$FOOO")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)

            val javaFile = JavaFile.builder(elementUtils.getPackageOf(it).qualifiedName.toString(), builder.build())
                    .addFileComment("Generated code from KeyEventDispatcher. Do not modify!")
                    .build()
            javaFile.writeTo(filer)
            javaFile.writeTo(System.out)
        }
        return true
    }
}