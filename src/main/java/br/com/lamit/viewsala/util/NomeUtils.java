package br.com.lamit.viewsala.util;

import br.com.lamit.viewsala.exception.NomeInvalidoException;

public class NomeUtils {

    public static String padronizarNomeSala(String nome) {

        if(nome == null || nome.isBlank()) {
            throw new NomeInvalidoException("O nome da sala é obrigatório");
        }

        String nomeNormalizado = nome.strip();


        nomeNormalizado = nomeNormalizado.substring(0, 1).toUpperCase() + nomeNormalizado.substring(1).toLowerCase();

        return nomeNormalizado;
    }

    public static String padronizarNomeProfessor(String nome) {
        if(nome == null || nome.isBlank()) {
            throw new NomeInvalidoException("O nome do professor é obrigatório");
        }
        nome = nome.strip();
        String[] nomeDividido = nome.split("\\s+");
        for(int i = 0; i < nomeDividido.length; i++) {
            nomeDividido[i] = nomeDividido[i].substring(0, 1).toUpperCase() + nomeDividido[i].substring(1).toLowerCase();
        }

        return String.join(" ", nomeDividido);
    }
}
