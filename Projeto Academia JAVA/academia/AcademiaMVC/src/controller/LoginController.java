package controller;

import dao.AlunoDAO;
import dao.InstrutorDAO;
import model.AlunoCadastrado;
import model.Instrutor;

public class LoginController {
    // Usuário fixo apenas para fins acadêmicos
    private static final String USUARIO_ADMIN = "admin";
    private static final String SENHA_ADMIN = "1234";

    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final InstrutorDAO instrutorDAO = new InstrutorDAO();

    /** @return null se o login estiver certo; senão, a mensagem de erro. */
    public String autenticar(String usuario, String senha) {
        if (Validador.vazio(usuario) || Validador.vazio(senha)) {
            return "Por favor, preencha usuário e senha.";
        }
        if (usuario.trim().equals(USUARIO_ADMIN) && senha.equals(SENHA_ADMIN)) {
            return null;
        }
        try {
            // Aluno ou instrutor cadastrado entra com e-mail + senha
            AlunoCadastrado a = alunoDAO.buscarPorEmail(usuario.trim());
            if (a != null && a.getSenha().equals(senha)) {
                return null;
            }
            Instrutor i = instrutorDAO.buscarPorEmail(usuario.trim());
            if (i != null && i.getSenha().equals(senha)) {
                return null;
            }
        } catch (RuntimeException e) {
            return "Erro ao acessar o banco de dados: " + e.getMessage();
        }
        return "Usuário ou senha inválidos.";
    }
}
