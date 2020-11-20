package com.landlordready.propertymanagement;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.landlordready.propertymanagement");

        noClasses()
            .that()
            .resideInAnyPackage("com.landlordready.propertymanagement.service..")
            .or()
            .resideInAnyPackage("com.landlordready.propertymanagement.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.landlordready.propertymanagement.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
