package net.zero.annotation.processor.catalogue

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration

interface CatalogueValidator {

    fun validateClass(type: KSClassDeclaration)

    fun validateProperty(property: KSPropertyDeclaration, type: KSClassDeclaration)
}
