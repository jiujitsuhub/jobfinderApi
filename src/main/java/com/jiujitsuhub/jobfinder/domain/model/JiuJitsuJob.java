package com.jiujitsuhub.jobfinder.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter

public class JiuJitsuJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private UserReference creator;
    private String description;
    private String legalObligations;
    @Column(columnDefinition = "DATE")
    private LocalDate startingDate;
    @Column(columnDefinition = "DATE")
    private LocalDate endingDate;
    @Enumerated(EnumType.STRING)
    private PublishedStatus publishedStatus;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Embedded
    private Payment payment;


}
