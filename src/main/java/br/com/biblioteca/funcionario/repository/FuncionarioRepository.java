package br.com.biblioteca.funcionario.repository;

import br.com.biblioteca.funcionario.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query("SELECT f.cep, COUNT(f.id) " +
            "FROM Funcionario f " +
            "GROUP BY f.cep " +
            "ORDER BY COUNT(f.id) DESC")
    List<Object[]> buscarCepComMaiorIncidencia();
}
