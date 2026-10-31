package view;

import controller.InstrutorController;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Instrutor;

public class AlterarInstrutor extends JFrame {

    private final InstrutorController controller = new InstrutorController();
    private final PainelPessoa painel = new PainelPessoa();
    private final Instrutor original;
    private final Runnable aoSalvar;

    private JTextField txFuncao;

    public AlterarInstrutor(Instrutor instrutor, Runnable aoSalvar) {
        this.original = instrutor;
        this.aoSalvar = aoSalvar;

        setTitle("Alterar Instrutor");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        add(painel);
        painel.preencher(instrutor);

        JLabel lbFuncao = new JLabel("Função: ");
        lbFuncao.setBounds(40, 280, 100, 25);
        add(lbFuncao);

        txFuncao = new JTextField(instrutor.getFuncao());
        txFuncao.setBounds(150, 280, 220, 25);
        add(txFuncao);

        JButton btSalvar = new JButton("Salvar alterações");
        btSalvar.setBounds(150, 330, 160, 30);
        add(btSalvar);
        btSalvar.addActionListener(e -> salvar());

        setVisible(true);
    }

    private void salvar() {
        Instrutor instrutor = new Instrutor(
                painel.getNome(), painel.getCpf(), painel.getEmail(), painel.getTelefone(),
                painel.getIdade(), painel.getEndereco(), painel.getSexo(), painel.getSenha(),
                txFuncao.getText());
        instrutor.setId(original.getId()); // o id identifica qual registro será alterado

        String erro = controller.alterar(instrutor);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro, "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Instrutor alterado com sucesso!");
        aoSalvar.run();
        dispose();
    }
}
