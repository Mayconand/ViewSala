package br.com.lamit.viewsala.repository;

import br.com.lamit.viewsala.entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia,Long> {
}
