package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Plano;

public class PlanoDAO {

    public void salvar(Plano plano) {
        String sql = "INSERT INTO tbPlano (nome, valor, duracao, tipo) VALUES (?, ?, ?, ?)";

        try (Connection conexao = new Conection().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, plano.getNomePlano());
            stmt.setDouble(2, plano.getPreco());
            stmt.setInt(3, plano.getDuracao());
            stmt.setString(4, plano.getIntervaloPagamento());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Plano> listar() {
        List<Plano> planos = new ArrayList<>();
        String sql = "SELECT * FROM tbPlano";

        try (Connection conexao = new Conection().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                planos.add(montar(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return planos;
    }

    public Plano buscarPorId(int id) {
        String sql = "SELECT * FROM tbPlano WHERE idPlano = ?";

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

    private Plano montar(ResultSet rs) throws SQLException {
        Plano plano = new Plano(
                rs.getString("nome"),
                rs.getDouble("valor"),
                rs.getInt("duracao"),
                rs.getString("tipo")
        );
        plano.setId(rs.getInt("idPlano"));
        return plano;
    }
}
