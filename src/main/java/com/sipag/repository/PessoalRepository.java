package com.sipag.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sipag.entity.Pessoa;

@Repository
public interface PessoalRepository extends CrudRepository<Pessoa, Long>{
	Optional<Pessoa> findByEmail(String email);
	List<Pessoa> findByNomeContainingIgnoreCase(String nome, Pageable pegeable);
}
