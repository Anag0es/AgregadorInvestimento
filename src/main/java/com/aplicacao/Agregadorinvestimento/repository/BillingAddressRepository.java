package com.aplicacao.Agregadorinvestimento.repository;

import com.aplicacao.Agregadorinvestimento.entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long> {
}
