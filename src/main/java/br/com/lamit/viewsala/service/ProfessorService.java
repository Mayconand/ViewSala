package br.com.lamit.viewsala.service;

import br.com.lamit.viewsala.entity.Professor;
import br.com.lamit.viewsala.exception.IdInvalidoException;

import br.com.lamit.viewsala.exception.ObjetoNaoEncontradoException;
import br.com.lamit.viewsala.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.lamit.viewsala.util.NomeUtils.padronizarNomeProfessor;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor criarProfessor(String nome) {

        String nomeNormalizado = padronizarNomeProfessor(nome);
        return professorRepository.save(new Professor(nomeNormalizado));

    }

    public Professor buscarProfessorPorId(Long id) {
        if(id==null){
            throw new IdInvalidoException("Id invalido");
        }

        return professorRepository.findById(id).orElseThrow(() ->
                new ObjetoNaoEncontradoException("Professor nao encontrado"));
    }

    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public Professor atualizarProfessor(Long id, String nome) {

        Professor professor = buscarProfessorPorId(id);

        String nomePadronizado = padronizarNomeProfessor(nome);
        professor.setNome(nomePadronizado);

        return professorRepository.save(professor);
    }

    public void deletarProfessor(Long id) {
        Professor professor = buscarProfessorPorId(id);
        professorRepository.delete(professor);
    }


}
