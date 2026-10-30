package view;

import controller.AlunoController;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.AlunoCadastrado;
import model.Instrutor;
import model.Plano;

public class CadastrarAluno extends JFrame {

    private final AlunoController controller = new AlunoController();
    private final PainelPessoa painel = new PainelPessoa();

    private JComboBox<Plano> cbPlano;
    private JComboBox<Instrutor> cbInstrutor;

    public CadastrarAluno() {
        setTitle("Cadastrar Aluno");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        add(painel);

        addLabel("Plano: ", 280);
        cbPlano = new JComboBox<>(controller.listarPlanos().toArray(new Plano[0]));
        cbPlano.setBounds(150, 280, 250, 25);
        add(cbPlano);

        addLabel("Instrutor: ", 320);
        cbInstrutor = new JComboBox<>(controller.listarInstrutores().toArray(new Instrutor[0]));
        cbInstrutor.setBounds(150, 320, 250, 25);
        add(cbInstrutor);

        JButton btSalvar = new JButton("Salvar");
        btSalvar.setBounds(150, 370, 120, 30);
        add(btSalvar);
        btSalvar.addActionListener(e -> salvar());

        setVisible(true);
    }

    private void salvar() {
        AlunoCadastrado aluno = new AlunoCadastrado(
                painel.getNome(), painel.getCpf(), painel.getEmail(), painel.getTelefone(),
                painel.getIdade(), painel.getEndereco(), painel.getSexo(), painel.getSenha(),
                (Plano) cbPlano.getSelectedItem(), (Instrutor) cbInstrutor.getSelectedItem());

        String erro = controller.cadastrar(aluno);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro, "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
        dispose();
    }

    private void addLabel(String texto, int y) {
        JLabel lb = new JLabel(texto);
        lb.setBounds(40, y, 100, 25);
        add(lb);
    }
}
