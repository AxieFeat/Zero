package net.zero.annotation.processor.immutable

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import net.zero.annotation.ImmutableTypeIgnore
import net.zero.annotation.processor.util.ContextualVisitor
import net.zero.annotation.processor.util.VisitorContext

@OptIn(KspExperimental::class)
object ImmutabilityChecker : ContextualVisitor() {

    private const val LOG_IGNORES = false

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: VisitorContext) {
        val validator = when (classDeclaration.classKind) {
            ClassKind.CLASS -> ClassImmutabilityValidator
            ClassKind.INTERFACE -> InterfaceImmutabilityValidator
            else -> return
        }
        validator.validateClass(classDeclaration, data.resolver)
        classDeclaration.getDeclaredProperties().map { visitProperty(it, classDeclaration, data, validator) }
    }

    private fun visitProperty(property: KSPropertyDeclaration, type: KSClassDeclaration, context: VisitorContext, validator: ImmutabilityValidator) {
        if (property.isMutable) {
            error("Property ${property.simpleName.asString()} in immutable type ${type.simpleName.asString()} is mutable!")
        }
        if (property.isAnnotationPresent(ImmutableTypeIgnore::class)) {
            if (LOG_IGNORES) {
                context.logger.info("Ignoring property ${property.simpleName.asString()} in immutable type ${type.simpleName.asString()}")
            }
            return
        }
        validator.validateProperty(property, type, context.resolver)
    }
}
