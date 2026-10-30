package view;

import controller.AlunoController;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.AlunoCadastrado;

public class AlunosMatriculados extends JFrame {

    private final AlunoController controller = new AlunoController();

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
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        for (AlunoCadastrado aluno : controller.listarAlunos()) {
            modelo.addRow(new Object[]{
                    aluno.getNome(),
                    aluno.getCpf(),
                    aluno.getEmail(),
                    aluno.getTelefone(),
                    aluno.getPlanoEscolhido() != null ? aluno.getPlanoEscolhido() : "-",
                    aluno.getInstrutorResponsavel() != null ? aluno.getInstrutorResponsavel() : "-"
            });
        }

        JScrollPane scroll = new JScrollPane(new JTable(modelo));
        scroll.setBounds(40, 55, 700, 280);
        add(scroll);

        setVisible(true);
    }
}
