package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;


@Table
@Entity
public class University extends BaseEntity<Long> {
    private static final String UNI_NAME = "uni_name";
    @Column(nullable = false, name = UNI_NAME)
    @Size(min = 4, max = 100, message = "minimum  length is 4 and max is 100")
    private String UniName;
}
