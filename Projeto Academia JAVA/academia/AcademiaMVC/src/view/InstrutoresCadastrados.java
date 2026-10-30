package view;

import controller.InstrutorController;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Instrutor;

public class InstrutoresCadastrados extends JFrame {

    private final InstrutorController controller = new InstrutorController();

    public InstrutoresCadastrados() {
        setTitle("Instrutores Cadastrados");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JLabel lbTitulo = new JLabel("Lista de Instrutores");
        lbTitulo.setBounds(150, 15, 300, 25);
        add(lbTitulo);

        String[] colunas = {"Nome", "CPF", "Email", "Telefone", "Função"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        for (Instrutor i : controller.listarInstrutores()) {
            modelo.addRow(new Object[]{
                    i.getNome(), i.getCpf(), i.getEmail(), i.getTelefone(), i.getFuncao()
            });
        }

        JScrollPane scroll = new JScrollPane(new JTable(modelo));
        scroll.setBounds(40, 55, 700, 280);
        add(scroll);

        setVisible(true);
    }
}
