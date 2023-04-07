package com.jiujitsuhub.jobfinder.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter

public class JiuJitsuJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    private String legalObligations;
    @Column(columnDefinition = "DATE")
    private LocalDate startingDate;
    @Column(columnDefinition = "DATE")
    private LocalDate endingDate;
    @Enumerated(EnumType.STRING)
    private PublishedStatus publishedStatus;
    @Embedded
    private Payment payment;


}
