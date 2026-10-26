package br.com.lamit.viewsala.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

    private LocalDateTime inicioAula;

    private LocalDateTime fimAula;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    public Aula(Materia materia, LocalDateTime inicioAula, LocalDateTime fimAula, Professor professor, Sala sala) {
        this.materia = materia;
        this.inicioAula = inicioAula;
        this.fimAula = fimAula;
        this.professor = professor;
        this.sala = sala;
    }

}
