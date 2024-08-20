package org.example.entities.validator.universitynamevalidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.repositories.UniversityRepository;

public class UniversityNameValidator implements ConstraintValidator<ValidUniversityName,String> {
    private final UniversityRepository universityRepository;

    // Assuming you are using Spring and injecting the repository
    public UniversityNameValidator(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public boolean isValid(String universityName, ConstraintValidatorContext context) {
        if (universityName == null || universityName.isEmpty()) {
            return false; // Or handle empty/null case as needed
        }

        // Check if the university name exists in the database
        return universityRepository.existsByName(universityName);
    }
}
