package view;

import controller.InstrutorController;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Instrutor;

public class CadastrarInstrutor extends JFrame {

    private final InstrutorController controller = new InstrutorController();
    private final PainelPessoa painel = new PainelPessoa();

    private JTextField txFuncao;

    public CadastrarInstrutor() {
        setTitle("Cadastrar Instrutor");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        add(painel);

        JLabel lbFuncao = new JLabel("Função: ");
        lbFuncao.setBounds(40, 280, 100, 25);
        add(lbFuncao);

        txFuncao = new JTextField();
        txFuncao.setBounds(150, 280, 220, 25);
        add(txFuncao);

        JButton btSalvar = new JButton("Salvar");
        btSalvar.setBounds(150, 330, 120, 30);
        add(btSalvar);
        btSalvar.addActionListener(e -> salvar());

        setVisible(true);
    }

    private void salvar() {
        Instrutor instrutor = new Instrutor(
                painel.getNome(), painel.getCpf(), painel.getEmail(), painel.getTelefone(),
                painel.getIdade(), painel.getEndereco(), painel.getSexo(), painel.getSenha(),
                txFuncao.getText());

        String erro = controller.cadastrar(instrutor);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro, "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Instrutor cadastrado com sucesso!");
        dispose();
    }
}
