package com.sancon.hrpayroll.operation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymantRepository extends JpaRepository<Payment, Long> {

}
