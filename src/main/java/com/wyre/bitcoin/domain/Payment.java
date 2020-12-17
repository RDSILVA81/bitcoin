package com.wyre.bitcoin.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "Payment")
@ToString
@SequenceGenerator(name="seqPay", initialValue = 1)
public class Payment {
    private String payFrom;
    private Date payDate;
    private double value;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPay")
    @Id private Integer id;
}
