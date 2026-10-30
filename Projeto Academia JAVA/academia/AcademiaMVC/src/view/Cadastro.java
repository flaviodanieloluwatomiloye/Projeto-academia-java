package view;

import controller.AlunoController;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.AlunoCadastrado;

public class Cadastro extends JFrame {

    private final AlunoController controller = new AlunoController();
    private final PainelPessoa painel = new PainelPessoa();

    public Cadastro() {
        setTitle("Registro de novo aluno");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        add(painel);

        JButton btSalvar = new JButton("Salvar");
        btSalvar.setBounds(150, 280, 100, 30);
        add(btSalvar);
        btSalvar.addActionListener(e -> salvar());

        setVisible(true);
    }

    private void salvar() {
        // Sem plano e sem instrutor: o aluno escolhe depois
        AlunoCadastrado aluno = new AlunoCadastrado(
                painel.getNome(), painel.getCpf(), painel.getEmail(), painel.getTelefone(),
                painel.getIdade(), painel.getEndereco(), painel.getSexo(), painel.getSenha(),
                null, null);

        String erro = controller.registrar(aluno);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro, "Campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        dispose();
        new Menu();
    }
}
