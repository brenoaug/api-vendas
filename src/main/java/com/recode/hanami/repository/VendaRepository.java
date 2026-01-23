package com.recode.hanami.repository;

import com.recode.hanami.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, String> {
    
    @Query("SELECT v FROM Venda v LEFT JOIN FETCH v.produto LEFT JOIN FETCH v.cliente LEFT JOIN FETCH v.vendedor")
    List<Venda> findAllWithRelations();

    @Query("SELECT v FROM Venda v LEFT JOIN FETCH v.produto LEFT JOIN FETCH v.cliente LEFT JOIN FETCH v.vendedor WHERE UPPER(v.cliente.estadoCliente) = UPPER(:estado)")
    List<Venda> findByClienteEstado(String estado);

    @Query("SELECT v FROM Venda v WHERE v.dataVenda BETWEEN :startDate AND :endDate")
    List<Venda> findByDataVendaBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}