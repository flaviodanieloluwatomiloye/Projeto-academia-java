package controller;
 
import dao.PlanoDAO;
import java.util.List;
import model.Plano;
 
public class PlanoController {
 
    private final PlanoDAO planoDAO = new PlanoDAO();
 
    public List<Plano> listarPlanos() {
        return planoDAO.listar();
    }
 
    /** Retorna null se deu certo; senão, a mensagem de erro. */
    public String cadastrar(Plano plano) {
        if (plano.getNomePlano() == null || plano.getNomePlano().trim().isEmpty()) {
            return "Informe o nome do plano.";
        }
        if (plano.getIntervaloPagamento() == null || plano.getIntervaloPagamento().trim().isEmpty()) {
            return "Informe o tipo do plano.";
        }
        if (plano.getPreco() <= 0) {
            return "Informe um valor válido para o plano.";
        }
        if (plano.getDuracao() <= 0) {
            return "Informe uma duração válida para o plano.";
        }
 
        try {
            planoDAO.salvar(plano);
        } catch (RuntimeException e) {
            return "Erro ao acessar o banco de dados: " + e.getMessage();
        }
        return null;
    }
}