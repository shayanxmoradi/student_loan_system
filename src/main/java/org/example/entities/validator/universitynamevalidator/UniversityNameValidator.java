package org.example.entities.validator.universitynamevalidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.repositories.UniversityRepo;

public class UniversityNameValidator implements ConstraintValidator<ValidUniversityName,String> {
    private final UniversityRepo universityRepo;

    public UniversityNameValidator(UniversityRepo universityRepo) {
        this.universityRepo = universityRepo;
    }

    @Override
    public boolean isValid(String universityName, ConstraintValidatorContext context) {
        if (universityName == null || universityName.isEmpty()) {
            return false;
        }

        return universityRepo.existsByName(universityName);
    }
}
