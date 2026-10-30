package controller;

import dao.AlunoDAO;
import dao.InstrutorDAO;
import dao.PlanoDAO;
import java.util.List;
import model.AlunoCadastrado;
import model.Instrutor;
import model.Plano;

public class AlunoController {
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final PlanoDAO planoDAO = new PlanoDAO();
    private final InstrutorDAO instrutorDAO = new InstrutorDAO();

    public List<AlunoCadastrado> listarAlunos() { return alunoDAO.listar(); }
    public List<Plano> listarPlanos() { return planoDAO.listar(); }
    public List<Instrutor> listarInstrutores() { return instrutorDAO.listar(); }

    /** Registro simples, sem plano (tela Cadastro). Retorna null se deu certo; senão, a mensagem de erro. */
    public String registrar(AlunoCadastrado aluno) {
        return salvar(aluno, false);
    }

    /** Matrícula com plano e instrutor (tela CadastrarAluno). Retorna null se deu certo; senão, a mensagem de erro. */
    public String cadastrar(AlunoCadastrado aluno) {
        return salvar(aluno, true);
    }

    private String salvar(AlunoCadastrado aluno, boolean exigePlano) {
        String erro = Validador.validarPessoa(aluno);
        if (erro != null) {
            return erro;
        }
        if (exigePlano && (aluno.getPlanoEscolhido() == null || aluno.getInstrutorResponsavel() == null)) {
            return "Cadastre ao menos um Plano e um Instrutor antes de continuar.";
        }
        try {
            if (alunoDAO.buscarPorCpf(aluno.getCpf()) != null) {
                return "Já existe um aluno cadastrado com esse CPF.";
            }
            if (alunoDAO.buscarPorEmail(aluno.getEmail()) != null) {
                return "Já existe um aluno cadastrado com esse e-mail.";
            }
            alunoDAO.salvar(aluno);
        } catch (RuntimeException e) {
            return "Erro ao acessar o banco de dados: " + e.getMessage();
        }
        return null;
    }
}
