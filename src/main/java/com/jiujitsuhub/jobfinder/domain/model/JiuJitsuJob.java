package com.jiujitsuhub.jobfinder.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class JiuJitsuJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(nullable = false)
    private UserReference creator;

    @Enumerated(EnumType.STRING)
    private PublishedStatus publishedStatus;
    @Embedded
    private JobDetails details;


}
