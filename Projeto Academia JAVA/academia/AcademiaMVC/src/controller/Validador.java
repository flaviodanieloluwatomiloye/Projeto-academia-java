package controller;

import model.Pessoa;

/** Validação comum a toda Pessoa (aluno ou instrutor). */
class Validador {

    static boolean vazio(String s) {
        return s == null || s.trim().isEmpty();
    }

    /** @return null se estiver tudo preenchido; senão, a mensagem de erro. */
    static String validarPessoa(Pessoa p) {
        if (vazio(p.getNome()) || vazio(p.getEmail()) || vazio(p.getEndereco())
                || vazio(p.getSexo()) || vazio(p.getSenha()) || vazio(p.getIdade())
                || vazio(p.getTelefone()) || p.getTelefone().contains("_")
                || vazio(p.getCpf()) || p.getCpf().contains("_")) {
            return "Por favor, preencha todos os campos antes de continuar.";
        }
        if (!p.getEmail().trim().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            return "Informe um e-mail válido.";
        }
        return null;
    }
}
