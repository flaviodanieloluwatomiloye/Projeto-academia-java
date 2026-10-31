package view;

import controller.InstrutorController;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Instrutor;

public class InstrutoresCadastrados extends JFrame {

    private final InstrutorController controller = new InstrutorController();
    private List<Instrutor> instrutores;
    private DefaultTableModel modelo;
    private JTable tabela;

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
        modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(40, 55, 700, 280);
        add(scroll);

        JButton btAlterar = new JButton("Alterar");
        btAlterar.setBounds(40, 350, 120, 30);
        add(btAlterar);
        btAlterar.addActionListener(e -> alterar());

        carregar();
        setVisible(true);
    }

    // Recarrega a tabela a partir do banco (chamado ao abrir e depois de cada alteração)
    private void carregar() {
        instrutores = controller.listarInstrutores();
        modelo.setRowCount(0);
        for (Instrutor i : instrutores) {
            modelo.addRow(new Object[]{
                    i.getNome(), i.getCpf(), i.getEmail(), i.getTelefone(), i.getFuncao()
            });
        }
    }

    private void alterar() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um instrutor na tabela para alterar.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new AlterarInstrutor(instrutores.get(linha), this::carregar);
    }
}
