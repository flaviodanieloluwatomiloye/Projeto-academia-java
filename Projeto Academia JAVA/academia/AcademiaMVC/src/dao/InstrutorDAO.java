package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Instrutor;

public class InstrutorDAO {

    public void salvar(Instrutor instrutor) {
        String sql = "INSERT INTO tbInstrutor (nome, email, telefone, cpf, idade, endereco, sexo, senha, funcao) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = new Conection().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, instrutor.getNome());
            stmt.setString(2, instrutor.getEmail());
            stmt.setString(3, instrutor.getTelefone());
            stmt.setString(4, instrutor.getCpf());
            stmt.setString(5, instrutor.getIdade());
            stmt.setString(6, instrutor.getEndereco());
            stmt.setString(7, instrutor.getSexo());
            stmt.setString(8, instrutor.getSenha());
            stmt.setString(9, instrutor.getFuncao());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Instrutor> listar() {
        List<Instrutor> instrutores = new ArrayList<>();
        String sql = "SELECT * FROM tbInstrutor";

        try (Connection conexao = new Conection().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                instrutores.add(montar(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return instrutores;
    }

    // ALTERAR: atualiza todos os dados do instrutor que tem o id informado
    public void alterar(Instrutor instrutor) {
        String sql = "UPDATE tbInstrutor SET nome = ?, email = ?, telefone = ?, cpf = ?, idade = ?, "
                   + "endereco = ?, sexo = ?, senha = ?, funcao = ? WHERE idInstrutor = ?";

        try (Connection conexao = new Conection().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, instrutor.getNome());
            stmt.setString(2, instrutor.getEmail());
            stmt.setString(3, instrutor.getTelefone());
            stmt.setString(4, instrutor.getCpf());
            stmt.setString(5, instrutor.getIdade());
            stmt.setString(6, instrutor.getEndereco());
            stmt.setString(7, instrutor.getSexo());
            stmt.setString(8, instrutor.getSenha());
            stmt.setString(9, instrutor.getFuncao());
            stmt.setInt(10, instrutor.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Instrutor buscarPorId(int id) {
        String sql = "SELECT * FROM tbInstrutor WHERE idInstrutor = ?";

        try (Connection conexao = new Conection().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return montar(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Instrutor buscarPorCpf(String cpf) {
        return buscarPor("cpf", cpf);
    }

    public Instrutor buscarPorEmail(String email) {
        return buscarPor("email", email);
    }

    // coluna vem sempre de constante interna (nunca do usuário)
    private Instrutor buscarPor(String coluna, String valor) {
        String sql = "SELECT * FROM tbInstrutor WHERE " + coluna + " = ?";

        try (Connection conexao = new Conection().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, valor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return montar(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private Instrutor montar(ResultSet rs) throws SQLException {
        Instrutor instrutor = new Instrutor(
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("email"),
                rs.getString("telefone"),
                rs.getString("idade"),
                rs.getString("endereco"),
                rs.getString("sexo"),
                rs.getString("senha"),
                rs.getString("funcao")
        );
        instrutor.setId(rs.getInt("idInstrutor"));
        return instrutor;
    }
}
