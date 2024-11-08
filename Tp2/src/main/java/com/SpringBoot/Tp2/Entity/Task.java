package com.SpringBoot.Tp2.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La description ne peut pas être vide.")
    @Size(max = 255, message = "La description ne doit pas dépasser 255 caractères.")
    private String description;

    @NotNull(message = "La date de création ne peut pas être nulle.")
    private Date creationDate;

    @NotNull(message = "La date d'échéance ne peut pas être nulle.")
    private Date enhanceDate;

}
