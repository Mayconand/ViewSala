package br.com.lamit.viewsala.repository;

import br.com.lamit.viewsala.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
}
