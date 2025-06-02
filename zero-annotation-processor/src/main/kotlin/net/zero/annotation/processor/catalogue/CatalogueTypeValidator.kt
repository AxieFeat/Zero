package net.zero.annotation.processor.catalogue

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getVisibility
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.Nullability
import com.google.devtools.ksp.symbol.Visibility
import net.zero.annotation.Catalogue
import net.zero.annotation.CataloguedBy
import net.zero.annotation.IgnoreNotCataloguedBy
import net.zero.annotation.processor.getClassArgumentByName
import net.zero.annotation.processor.getKSAnnotation

@OptIn(KspExperimental::class)
object CatalogueTypeValidator : CatalogueValidator {

    override fun validateClass(type: KSClassDeclaration) {
        if (type.isAnnotationPresent(IgnoreNotCataloguedBy::class)) return
        val cataloguedBy = getCataloguedType(type).getKSAnnotation(CataloguedBy::class)
        check(cataloguedBy.getClassArgumentByName("catalogue").qualifiedName?.asString() == type.qualifiedName!!.asString()) {
            "Catalogue mismatch for ${type.simpleName.asString()}!"
        }
    }

    override fun validateProperty(property: KSPropertyDeclaration, type: KSClassDeclaration) {
        if (property.getVisibility() != Visibility.PUBLIC) return // Only need to check public properties for compliance
        if (!property.isAnnotationPresent(JvmField::class)) fail(property, type, "must all be @JvmField")
        if (property.isMutable) fail(property, type, "must all be values")
        val propertyType = property.type.resolve()
        if (propertyType.nullability != Nullability.NOT_NULL) fail(property, type, "must all be non-null")

        val cataloguedType = getCataloguedType(type)
        if (!propertyType.isAssignableFrom(cataloguedType.asStarProjectedType())) {
            fail(property, type, "must all be of type ${cataloguedType.qualifiedName!!.asString()}")
        }
    }

    @JvmStatic
    private fun getCataloguedType(type: KSClassDeclaration): KSClassDeclaration =
        type.getKSAnnotation(Catalogue::class).getClassArgumentByName("type")

    @JvmStatic
    private fun fail(property: KSPropertyDeclaration, type: KSClassDeclaration, message: String) {
        error("Public properties in catalogue type ${type.simpleName.asString()} $message! ${property.simpleName.asString()} was not.")
    }
}
