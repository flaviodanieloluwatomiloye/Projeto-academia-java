package view;

import controller.AlunoController;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.AlunoCadastrado;

public class AlunosMatriculados extends JFrame {

    private final AlunoController controller = new AlunoController();
    private List<AlunoCadastrado> alunos;
    private DefaultTableModel modelo;
    private JTable tabela;

    public AlunosMatriculados() {
        setTitle("Alunos Matriculados");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JLabel lbTitulo = new JLabel("Lista de Alunos Matriculados");
        lbTitulo.setBounds(150, 15, 300, 25);
        add(lbTitulo);

        String[] colunas = {"Nome", "CPF", "Email", "Telefone", "Plano", "Instrutor"};
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
        alunos = controller.listarAlunos();
        modelo.setRowCount(0);
        for (AlunoCadastrado aluno : alunos) {
            modelo.addRow(new Object[]{
                    aluno.getNome(),
                    aluno.getCpf(),
                    aluno.getEmail(),
                    aluno.getTelefone(),
                    aluno.getPlanoEscolhido() != null ? aluno.getPlanoEscolhido() : "-",
                    aluno.getInstrutorResponsavel() != null ? aluno.getInstrutorResponsavel() : "-"
            });
        }
    }

    private void alterar() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno na tabela para alterar.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new AlterarAluno(alunos.get(linha), this::carregar);
    }
}
