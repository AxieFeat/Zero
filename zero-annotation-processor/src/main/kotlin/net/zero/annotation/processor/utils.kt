package net.zero.annotation.processor

import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import kotlin.reflect.KClass

fun <T : Annotation> KSAnnotated.getKSAnnotation(annotationType: KClass<T>): KSAnnotation = annotations
    .filter { it.shortName.getShortName() == annotationType.simpleName }
    .filter { it.annotationType.resolve().declaration.qualifiedName?.asString() == annotationType.qualifiedName }
    .singleOrNull()
    ?: error("Cannot find ${annotationType.simpleName} on type $this")

fun KSAnnotation.getClassArgumentByName(name: String): KSClassDeclaration {
    val argument = arguments.firstOrNull { it.name?.asString() == name }?.value
        ?: throw AssertionError("No argument with name $name! Was $shortName updated?")
    if (argument !is KSType) throw AssertionError("Value of argument $name was not a type! Was $shortName updated?")
    return argument.declaration as? KSClassDeclaration
        ?: throw AssertionError("Type declaration for argument $name was not a class! Was $shortName updated?")
}
