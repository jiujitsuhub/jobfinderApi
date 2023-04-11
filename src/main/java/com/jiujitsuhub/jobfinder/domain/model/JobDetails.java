package com.jiujitsuhub.jobfinder.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class JobDetails {
    @Column(nullable = false)
    private String title;
    private String description;
    private String legalObligations;
    @Column(columnDefinition = "DATE")
    private LocalDate startingDate;
    @Column(columnDefinition = "DATE")
    private LocalDate endingDate;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Embedded
    private Payment payment;
}
