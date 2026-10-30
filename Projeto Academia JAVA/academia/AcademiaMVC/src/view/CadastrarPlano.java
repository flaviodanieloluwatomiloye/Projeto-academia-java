package view;

import controller.PlanoController;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Plano;

public class CadastrarPlano extends JFrame {

    private final PlanoController controller = new PlanoController();

    private JTextField txNome, txPreco, txDuracao;
    private JComboBox<String> cbTipo;

    public CadastrarPlano() {
        setTitle("Cadastrar Plano");
        setSize(400, 280);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        addLabel("Nome: ", 20);
        txNome = new JTextField();
        txNome.setBounds(150, 20, 200, 25);
        add(txNome);

        addLabel("Preço (R$): ", 60);
        txPreco = new JTextField();
        txPreco.setBounds(150, 60, 100, 25);
        add(txPreco);

        addLabel("Duração (meses): ", 100);
        txDuracao = new JTextField();
        txDuracao.setBounds(150, 100, 60, 25);
        add(txDuracao);

        addLabel("Tipo: ", 140);
        cbTipo = new JComboBox<>(new String[]{"Mensal", "Trimestral", "Semestral", "Anual"});
        cbTipo.setBounds(150, 140, 150, 25);
        add(cbTipo);

        JButton btSalvar = new JButton("Salvar");
        btSalvar.setBounds(150, 190, 120, 30);
        add(btSalvar);
        btSalvar.addActionListener(e -> salvar());

        setVisible(true);
    }

    private void salvar() {
        double preco;
        int duracao;

        try {
            preco = Double.parseDouble(txPreco.getText().replace(",", "."));
            duracao = Integer.parseInt(txDuracao.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preço e duração precisam ser números.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Plano plano = new Plano(txNome.getText(), preco, duracao, (String) cbTipo.getSelectedItem());

        String erro = controller.cadastrar(plano);
        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro, "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Plano cadastrado com sucesso!");
        dispose();
    }

    private void addLabel(String texto, int y) {
        JLabel lb = new JLabel(texto);
        lb.setBounds(20, y, 120, 25);
        add(lb);
    }
}