package com.finch.App.processo.seletivo.Finch.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.finch.App.processo.seletivo.Finch.Model.LancheIngrediente;

public interface LancheIngredienteRepository  extends JpaRepository<LancheIngrediente, Long> {
	@Query(value = "SELECT * FROM lanche_ingrediente "
			 + "WHERE lanche_id = :id_lanche", nativeQuery = true)
	List<LancheIngrediente> findIngredienteByLanche(long id_lanche);
}
