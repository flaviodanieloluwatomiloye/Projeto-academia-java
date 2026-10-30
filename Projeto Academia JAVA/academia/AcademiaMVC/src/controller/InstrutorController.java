package controller;

import dao.InstrutorDAO;
import java.util.List;
import model.Instrutor;

public class InstrutorController {
    private final InstrutorDAO instrutorDAO = new InstrutorDAO();

    public List<Instrutor> listarInstrutores() { return instrutorDAO.listar(); }

    /** Retorna null se deu certo; senão, a mensagem de erro. */
    public String cadastrar(Instrutor instrutor) {
        String erro = Validador.validarPessoa(instrutor);
        if (erro != null) {
            return erro;
        }
        if (Validador.vazio(instrutor.getFuncao())) {
            return "Informe a função do instrutor.";
        }
        try {
            if (instrutorDAO.buscarPorCpf(instrutor.getCpf()) != null) {
                return "Já existe um instrutor cadastrado com esse CPF.";
            }
            if (instrutorDAO.buscarPorEmail(instrutor.getEmail()) != null) {
                return "Já existe um instrutor cadastrado com esse e-mail.";
            }
            instrutorDAO.salvar(instrutor);
        } catch (RuntimeException e) {
            return "Erro ao acessar o banco de dados: " + e.getMessage();
        }
        return null;
    }
}
