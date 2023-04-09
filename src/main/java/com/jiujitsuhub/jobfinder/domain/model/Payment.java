package com.jiujitsuhub.jobfinder.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@AllArgsConstructor
@Getter
@Setter
@Embeddable
@NoArgsConstructor

public class Payment {
    private BigDecimal amount;
    private PaymentType type;

}
