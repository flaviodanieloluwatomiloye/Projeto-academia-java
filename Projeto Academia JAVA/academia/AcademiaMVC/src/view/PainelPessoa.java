package view;

import java.text.ParseException;
import model.Pessoa;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/** Campos comuns a toda Pessoa. Ocupa a área de y=0 até y=270 do formulário. */
public class PainelPessoa extends JPanel {

    private JTextField txNome, txEmail, txEndereco;
    private JFormattedTextField txCPF, txTelefone;
    private JComboBox<String> cbIdade;
    private JRadioButton rbF, rbM, rbO;
    private JPasswordField paSenha;

    public PainelPessoa() {
        setLayout(null);
        setBounds(0, 0, 800, 270);

        addLabel("Nome: ", 10);
        txNome = new JTextField();
        txNome.setBounds(150, 10, 220, 25);
        add(txNome);

        addLabel("Email: ", 40);
        txEmail = new JTextField();
        txEmail.setBounds(150, 40, 220, 25);
        add(txEmail);

        addLabel("Telefone: ", 70);
        txTelefone = criarCampoMascara("(##) #####-####");
        txTelefone.setBounds(150, 70, 150, 25);
        add(txTelefone);

        addLabel("CPF: ", 100);
        txCPF = criarCampoMascara("###.###.###-##");
        txCPF.setBounds(150, 100, 150, 25);
        add(txCPF);

        addLabel("Idade: ", 130);
        String[] idades = new String[83];
        for (int i = 0; i < idades.length; i++) idades[i] = String.valueOf(i + 18);
        cbIdade = new JComboBox<>(idades);
        cbIdade.setBounds(150, 130, 80, 25);
        add(cbIdade);

        addLabel("Endereço: ", 160);
        txEndereco = new JTextField();
        txEndereco.setBounds(150, 160, 250, 25);
        add(txEndereco);

        addLabel("Sexo: ", 190);
        rbF = new JRadioButton("Feminino");
        rbM = new JRadioButton("Masculino");
        rbO = new JRadioButton("Outros");
        rbF.setBounds(150, 190, 90, 25);
        rbM.setBounds(245, 190, 95, 25);
        rbO.setBounds(345, 190, 90, 25);
        ButtonGroup grupo = new ButtonGroup();
        for (JRadioButton rb : new JRadioButton[]{rbF, rbM, rbO}) {
            grupo.add(rb);
            add(rb);
        }

        addLabel("Senha: ", 230);
        paSenha = new JPasswordField();
        paSenha.setBounds(150, 230, 150, 25);
        add(paSenha);
    }

    public String getNome() { return txNome.getText(); }
    public String getEmail() { return txEmail.getText(); }
    public String getTelefone() { return txTelefone.getText(); }
    public String getCpf() { return txCPF.getText(); }
    public String getIdade() { return (String) cbIdade.getSelectedItem(); }
    public String getEndereco() { return txEndereco.getText(); }
    public String getSenha() { return new String(paSenha.getPassword()); }

    public String getSexo() {
        if (rbF.isSelected()) return rbF.getText();
        if (rbM.isSelected()) return rbM.getText();
        if (rbO.isSelected()) return rbO.getText();
        return "";
    }

    /** Preenche os campos com os dados de uma pessoa (usado nas telas de alteração). */
    public void preencher(Pessoa p) {
        txNome.setText(p.getNome());
        txEmail.setText(p.getEmail());
        txTelefone.setText(p.getTelefone());
        txCPF.setText(p.getCpf());
        cbIdade.setSelectedItem(p.getIdade());
        txEndereco.setText(p.getEndereco());
        rbF.setSelected(rbF.getText().equals(p.getSexo()));
        rbM.setSelected(rbM.getText().equals(p.getSexo()));
        rbO.setSelected(rbO.getText().equals(p.getSexo()));
        paSenha.setText(p.getSenha());
    }

    private void addLabel(String texto, int y) {
        JLabel lb = new JLabel(texto);
        lb.setBounds(40, y, 100, 25);
        add(lb);
    }

    private JFormattedTextField criarCampoMascara(String mascara) {
        try {
            MaskFormatter mf = new MaskFormatter(mascara);
            mf.setPlaceholderCharacter('_');
            return new JFormattedTextField(mf);
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }
}
