package br.com.erudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.erudio.data.model.Breakfast;

@Repository
public interface BreakfastRepository extends JpaRepository<Breakfast, Long>{

}