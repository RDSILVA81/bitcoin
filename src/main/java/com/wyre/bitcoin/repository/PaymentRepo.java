package com.wyre.bitcoin.repository;

import com.wyre.bitcoin.domain.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepo extends CrudRepository<Payment, Long> {
}
