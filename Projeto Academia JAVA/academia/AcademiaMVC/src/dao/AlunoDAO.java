package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.AlunoCadastrado;
import model.Instrutor;
import model.Plano;

public class AlunoDAO {

    // Salva um novo aluno. Plano e instrutor podem ser null.
    public void salvar(AlunoCadastrado aluno) {
        String sql = "INSERT INTO tbAluno (nome, email, telefone, cpf, idade, endereco, sexo, senha, idPlano, idInstrutor) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = new Conection().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());
            stmt.setString(4, aluno.getCpf());
            stmt.setString(5, aluno.getIdade());
            stmt.setString(6, aluno.getEndereco());
            stmt.setString(7, aluno.getSexo());
            stmt.setString(8, aluno.getSenha());

            if (aluno.getPlanoEscolhido() != null) {
                stmt.setInt(9, aluno.getPlanoEscolhido().getId());
            } else {
                stmt.setNull(9, Types.INTEGER);
            }

            if (aluno.getInstrutorResponsavel() != null) {
                stmt.setInt(10, aluno.getInstrutorResponsavel().getId());
            } else {
                stmt.setNull(10, Types.INTEGER);
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AlunoCadastrado> listar() {
        List<AlunoCadastrado> alunos = new ArrayList<>();
        String sql = "SELECT * FROM tbAluno";

        try (Connection conexao = new Conection().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                alunos.add(montarAluno(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alunos;
    }

    public AlunoCadastrado buscarPorCpf(String cpf) {
        return buscarPor("cpf", cpf);
    }

    public AlunoCadastrado buscarPorEmail(String email) {
        return buscarPor("email", email);
    }

    // coluna vem sempre de constante interna (nunca do usuário)
    private AlunoCadastrado buscarPor(String coluna, String valor) {
        String sql = "SELECT * FROM tbAluno WHERE " + coluna + " = ?";

        try (Connection conexao = new Conection().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, valor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return montarAluno(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    // ALTERAR: atualiza todos os dados do aluno que tem o id informado
    public void alterar(AlunoCadastrado aluno) {
        String sql = "UPDATE tbAluno SET nome = ?, email = ?, telefone = ?, cpf = ?, idade = ?, "
                   + "endereco = ?, sexo = ?, senha = ?, idPlano = ?, idInstrutor = ? WHERE idAluno = ?";

        try (Connection conexao = new Conection().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());
            stmt.setString(4, aluno.getCpf());
            stmt.setString(5, aluno.getIdade());
            stmt.setString(6, aluno.getEndereco());
            stmt.setString(7, aluno.getSexo());
            stmt.setString(8, aluno.getSenha());

            if (aluno.getPlanoEscolhido() != null) {
                stmt.setInt(9, aluno.getPlanoEscolhido().getId());
            } else {
                stmt.setNull(9, Types.INTEGER);
            }

            if (aluno.getInstrutorResponsavel() != null) {
                stmt.setInt(10, aluno.getInstrutorResponsavel().getId());
            } else {
                stmt.setNull(10, Types.INTEGER);
            }

            stmt.setInt(11, aluno.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean remover(String cpf) {
        String sql = "DELETE FROM tbAluno WHERE cpf = ?";

        try (Connection conexao = new Conection().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private AlunoCadastrado montarAluno(ResultSet rs) throws SQLException {
        // getInt devolve 0 quando a coluna é NULL; wasNull() diferencia "sem plano" de id 0
        int idPlano = rs.getInt("idPlano");
        Plano plano = rs.wasNull() ? null : new PlanoDAO().buscarPorId(idPlano);

        int idInstrutor = rs.getInt("idInstrutor");
        Instrutor instrutor = rs.wasNull() ? null : new InstrutorDAO().buscarPorId(idInstrutor);

        AlunoCadastrado aluno = new AlunoCadastrado(
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("email"),
                rs.getString("telefone"),
                rs.getString("idade"),
                rs.getString("endereco"),
                rs.getString("sexo"),
                rs.getString("senha"),
                plano,
                instrutor
        );
        aluno.setId(rs.getInt("idAluno"));
        return aluno;
    }
}
