package com.wyre.bitcoin.repository;

import com.wyre.bitcoin.domain.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends CrudRepository<Payment, Long> {
}
