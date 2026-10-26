package br.com.lamit.viewsala.service;

import br.com.lamit.viewsala.entity.Sala;
import br.com.lamit.viewsala.exception.IdInvalidoException;
import br.com.lamit.viewsala.exception.ObjetoDuplicadoException;
import br.com.lamit.viewsala.exception.ObjetoNaoEncontradoException;
import br.com.lamit.viewsala.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.lamit.viewsala.util.NomeUtils.padronizarNomeSala;


@Service
public class SalaService {


    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public Sala criarSala(String nome) {

        String novoNome = padronizarNomeSala(nome);
        if(salaRepository.existsByNomeIgnoreCase(novoNome)) {
            throw new ObjetoDuplicadoException("O nome da sala já existe");
        }
        return salaRepository.save(new Sala(novoNome));
    }


    public List<Sala> listarTodasSalas () {
        return salaRepository.findAll();
    }

    public Sala buscarSalaPorId(Long id) {

      if(id == null) {
          throw new IdInvalidoException("Id invalido");
      }

      return salaRepository.findById(id).orElseThrow(() ->
              new ObjetoNaoEncontradoException("Sala não encontrada"));

    }

    public Sala atualizarSala(Long id,String novoNome) {

        Sala sala = buscarSalaPorId(id);

        String novoNomePadronizado = padronizarNomeSala(novoNome);
        if (salaRepository.existsByNomeIgnoreCaseAndIdNot(novoNomePadronizado, id)) {
            throw new ObjetoDuplicadoException("Já existe uma sala com esse novo nome");
        }
        sala.setNome(novoNomePadronizado);
        return salaRepository.save(sala);
    }

    public void deletarSala(Long id) {

        Sala sala = buscarSalaPorId(id);
        salaRepository.delete(sala);

    }


}
