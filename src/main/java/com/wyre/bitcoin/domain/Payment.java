package com.wyre.bitcoin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity(name = "Payment")
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private String payFrom;
    private Date payDate;
    private double value;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
