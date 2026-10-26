package br.com.lamit.viewsala.service;


import br.com.lamit.viewsala.entity.Sala;
import br.com.lamit.viewsala.exception.IdInvalidoException;
import br.com.lamit.viewsala.exception.NomeInvalidoException;
import br.com.lamit.viewsala.exception.ObjetoDuplicadoException;
import br.com.lamit.viewsala.exception.ObjetoNaoEncontradoException;
import br.com.lamit.viewsala.repository.SalaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalaServiceTest {

    SalaRepository repositoryFalso = Mockito.mock(SalaRepository.class);

    SalaService salaService = new SalaService(repositoryFalso);

    @Test
    public void criarSalaNullTest(){
        Assertions.assertThrows(NomeInvalidoException.class, () -> salaService.criarSala(null));
    }

    @Test
    public void criarSalaEmBrancoTest(){
        Assertions.assertThrows(NomeInvalidoException.class, () -> salaService.criarSala(" "));
    }

    @Test
    public void criarSalaVazioTest(){
        Assertions.assertThrows(NomeInvalidoException.class, () -> salaService.criarSala(""));
    }

    @Test
    public void criarSalaDuplicadaTest(){
        Mockito.when(repositoryFalso.existsByNomeIgnoreCase("Sala 1")).thenReturn(true);
        Assertions.assertThrows(ObjetoDuplicadoException.class, () -> salaService.criarSala("Sala 1"));
    }

    @Test
    public void criarSalaCorretaTest(){
        salaService.criarSala("Sala 1");

        Mockito.verify(repositoryFalso).save(Mockito.any(Sala.class));

    }

    @Test
    public void criarSalaNomeCorretoTest(){
        salaService.criarSala("SAla 1 ");
        ArgumentCaptor<Sala> captor = ArgumentCaptor.forClass(Sala.class);
        Mockito.verify(repositoryFalso).save(captor.capture());

        Assertions.assertEquals("Sala 1", captor.getValue().getNome());
    }

    @Test
    public void criarSalaDeveRetornarSalaSalvaTest(){
        Sala salaSalva = new Sala("Sala 1");
        Mockito.when(repositoryFalso.save(Mockito.any(Sala.class))).thenReturn(salaSalva);

        Sala resultado = salaService.criarSala("Sala 1");

        Assertions.assertSame(salaSalva, resultado);
    }

    @Test
    public void listarSalaDeveRetornarListaSalasTest(){
        List<Sala> salas = new ArrayList<>();

        Sala sala1 = new Sala("Sala 1");
        Sala sala2 = new Sala("Sala 2");

        salas.add(sala1);
        salas.add(sala2);

        Mockito.when(repositoryFalso.findAll()).thenReturn(salas);

        List<Sala> listaResultado = salaService.listarTodasSalas();

        Assertions.assertSame(salas, listaResultado);
    }

    @Test
    public void listarSalaDeveRetornarListaVaziaTest(){
        List<Sala> salas = new ArrayList<>();

        Mockito.when(repositoryFalso.findAll()).thenReturn(salas);

        List<Sala> resultado = salaService.listarTodasSalas();

        Assertions.assertTrue(resultado.isEmpty());

    }

    @Test
    public void buscarSalaPorIdVazioTest(){

        Assertions.assertThrows(IdInvalidoException.class,() -> salaService.buscarSalaPorId(null));
    }

    @Test
    public void buscarSalaPorIdDeveRetornarSalaTest(){
        Sala sala = new Sala("Sala 1");
        Mockito.when(repositoryFalso.findById(1L)).thenReturn(Optional.of(sala));
        Sala resultado = salaService.buscarSalaPorId(1L);
        Assertions.assertSame(sala, resultado);
    }

    @Test
    public void buscarSalaPorIdDeveRetornarSalaNaoEncontradaTest(){
        Mockito.when(repositoryFalso.findById(99L)).thenReturn(Optional.empty());
        Assertions.assertThrows(ObjetoNaoEncontradoException.class,() -> salaService.buscarSalaPorId(99L));

    }

    @Test
    public void atualizarSalaPorIdAtualizaSalaTest(){
        Sala sala = new Sala("Sala 1");
        Mockito.when(repositoryFalso.findById(1L)).thenReturn(Optional.of(sala));
        Mockito.when(repositoryFalso.existsByNomeIgnoreCaseAndIdNot("Sala 2", 1L)).thenReturn(false);
        Mockito.when(repositoryFalso.save(Mockito.any(Sala.class))).thenReturn(sala);
        Sala resultado = salaService.atualizarSala(1L, "Sala 2");
        Assertions.assertSame(sala, resultado);
        Assertions.assertEquals("Sala 2", resultado.getNome());
    }

    @Test
    public void atualizarSalaPorIdVazioTest(){

        Assertions.assertThrows(IdInvalidoException.class,() -> salaService.atualizarSala(null, "Sala 2"));
    }

    @Test
    public void atualizarSalaPorIdComNomeIncorretoTest(){
        Sala sala = new Sala("Sala 1");
        Mockito.when(repositoryFalso.findById(1L)).thenReturn(Optional.of(sala));
        Assertions.assertThrows(NomeInvalidoException.class,() -> salaService.atualizarSala(1L, ""));
    }

    @Test
    public void atualizarSalaPorIdComNomeDuplicadoTest(){
        Sala sala = new  Sala("Sala 1");
        Mockito.when(repositoryFalso.findById(1L)).thenReturn(Optional.of(sala));
        Mockito.when(repositoryFalso.existsByNomeIgnoreCaseAndIdNot("Sala 2", 1L)).thenReturn(true);
        Assertions.assertThrows(ObjetoDuplicadoException.class, ()  -> salaService.atualizarSala(1L, "Sala 2"));

    }

    @Test
    public void atualizarSalaPorIdNaoEncontradoTest(){
        Mockito.when(repositoryFalso.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(ObjetoNaoEncontradoException.class,() -> salaService.atualizarSala(1L, "Sala 1"));
    }

    @Test
    public void deletarSalaPorIdCorreto(){
        Sala sala = new Sala("Sala 1");
        Mockito.when(repositoryFalso.findById(1L)).thenReturn(Optional.of(sala));
        salaService.deletarSala(1L);
        Mockito.verify(repositoryFalso).delete(sala);
    }

    @Test
    public void deletarSalaPorIdNaoEncontradoTest(){
        Mockito.when(repositoryFalso.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(ObjetoNaoEncontradoException.class,() -> salaService.deletarSala(1L));

    }

    @Test
    public void deletarSalaPorIdInvalidoTest(){
        Assertions.assertThrows(IdInvalidoException.class,() -> salaService.deletarSala(null));
    }



}

