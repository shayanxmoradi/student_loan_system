package org.example.entities.validator.universitynamevalidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.repositories.UniversityRepo;

public class UniversityNameValidator implements ConstraintValidator<ValidUniversityName,String> {
    private final UniversityRepo universityRepo;

    // Assuming you are using Spring and injecting the repository
    public UniversityNameValidator(UniversityRepo universityRepo) {
        this.universityRepo = universityRepo;
    }

    @Override
    public boolean isValid(String universityName, ConstraintValidatorContext context) {
        if (universityName == null || universityName.isEmpty()) {
            return false; // Or handle empty/null case as needed
        }

        // Check if the university name exists in the database
        return universityRepo.existsByName(universityName);
    }
}
