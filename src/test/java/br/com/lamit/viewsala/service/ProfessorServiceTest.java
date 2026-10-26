package br.com.lamit.viewsala.service;

import br.com.lamit.viewsala.entity.Professor;
import br.com.lamit.viewsala.exception.NomeInvalidoException;
import br.com.lamit.viewsala.repository.ProfessorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ProfessorServiceTest {

    ProfessorRepository professorRepositoryFake = Mockito.mock(ProfessorRepository.class);

    ProfessorService professorService = new ProfessorService(professorRepositoryFake);

    @Test
    public void criarProfessorCorretoTest(){
        professorService.criarProfessor("Jõao");
        Mockito.verify(professorRepositoryFake).save(Mockito.any(Professor.class));
    }

    @Test
    public void criarProfessorNullTest(){
        Assertions.assertThrows(NomeInvalidoException.class, () -> professorService.criarProfessor(null));
    }

    @Test
    public void criarProfessorNomeInvalidoTest(){
        Assertions.assertThrows(NomeInvalidoException.class, () -> professorService.criarProfessor(""));
    }

    @Test
    public void criarProfessorNomeComEspacoTest(){
        Assertions.assertThrows(NomeInvalidoException.class, () -> professorService.criarProfessor(" "));
    }

    @Test
    public void criarProfessorNomePadronizadoTest(){
        professorService.criarProfessor("maycon andrade santos");

        ArgumentCaptor<Professor> captor = ArgumentCaptor.forClass(Professor.class);
        Mockito.verify(professorRepositoryFake).save(captor.capture());

        Assertions.assertEquals("Maycon Andrade Santos",captor.getValue().getNome());

    }

    @Test
    public void criarProfessorRetornaCorreto() {
        Professor professor = new Professor("Maycon Andrade Santos");
        Mockito.when(professorRepositoryFake.save(Mockito.any(Professor.class))).thenReturn(professor);

        Professor resultado = professorService.criarProfessor("Maycon Andrade Santos");
        Assertions.assertSame(resultado,professor);

    }

    @Test
    public void listarProfessoresTest(){
        List<Professor> professores = new ArrayList<>();

        Professor professor1 = new Professor("Maycon Andrade Santos");
        Professor professor2 = new Professor("Rodrigo Andrade Dos Santos");

        professores.add(professor1);
        professores.add(professor2);

        Mockito.when(professorRepositoryFake.findAll()).thenReturn(professores);
        List<Professor> resultado = professorService.listarProfessores();
        Assertions.assertSame(professores,resultado);
    }

    @Test
    public void listarProfessoresVaziaTest(){
        List<Professor> professores = new ArrayList<>();
        Mockito.when(professorRepositoryFake.findAll()).thenReturn(professores);

        Assertions.assertTrue(professorService.listarProfessores().isEmpty());
    }

}
