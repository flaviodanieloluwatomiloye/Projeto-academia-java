package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import model.AlunoCadastrado;
import model.Cadastro;

public class CadastroDAO {

	public void salvar(Cadastro aluno) {
        Connection conexao = new Conection().getConnection();
        String sql = "INSERT INTO tbCadastro (nome, senha) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(8, aluno.getSenha());

            stmt.executeUpdate();
 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
