package com.example.algamoney.api.repository;

import com.example.algamoney.api.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
