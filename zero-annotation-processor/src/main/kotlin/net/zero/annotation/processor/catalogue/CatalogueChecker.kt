package net.zero.annotation.processor.catalogue

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.symbol.KSClassDeclaration
import net.zero.annotation.Catalogue
import net.zero.annotation.CataloguedBy
import net.zero.annotation.processor.util.ContextualVisitor
import net.zero.annotation.processor.util.VisitorContext

object CatalogueChecker : ContextualVisitor() {

    @OptIn(KspExperimental::class)
    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: VisitorContext) {
        val validator = when {
            classDeclaration.isAnnotationPresent(Catalogue::class) -> CatalogueTypeValidator
            classDeclaration.isAnnotationPresent(CataloguedBy::class) -> CataloguedTypeValidator
            else -> return
        }
        validator.validateClass(classDeclaration)
        classDeclaration.getDeclaredProperties().map { validator.validateProperty(it, classDeclaration) }
    }
}
