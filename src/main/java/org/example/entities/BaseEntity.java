package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity {
    private static final String ID = "id";

    @Id
    @GeneratedValue
    @Column(name =ID )
    private Long id;
}
