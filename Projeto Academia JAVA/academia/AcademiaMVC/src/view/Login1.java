package view;

import controller.LoginController;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login1 extends JFrame {

    private final LoginController controller = new LoginController();

    private JTextField txUsuario;
    private JPasswordField paSenha;

    public Login1() {
        setTitle("Login - Academia");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JLabel lbTitulo = new JLabel("Acesso ao Sistema");
        lbTitulo.setBounds(100, 20, 250, 30);
        add(lbTitulo);

        JLabel lbUsuario = new JLabel("Usuário: ");
        lbUsuario.setBounds(40, 80, 100, 25);
        add(lbUsuario);

        txUsuario = new JTextField();
        txUsuario.setBounds(140, 80, 180, 25);
        add(txUsuario);

        JLabel lbSenha = new JLabel("Senha: ");
        lbSenha.setBounds(40, 120, 100, 25);
        add(lbSenha);

        paSenha = new JPasswordField();
        paSenha.setBounds(140, 120, 180, 25);
        add(paSenha);

        JButton btEntrar = new JButton("Entrar");
        btEntrar.setBounds(80, 170, 100, 30);
        add(btEntrar);

        JButton btCadastrar = new JButton("Cadastrar");
        btCadastrar.setBounds(200, 170, 110, 30);
        add(btCadastrar);

        btEntrar.addActionListener(e -> entrar());

        btCadastrar.addActionListener(e -> {
            dispose();
            new Cadastro();
        });

        setVisible(true);
    }

    private void entrar() {
        String senha = new String(paSenha.getPassword());
        String erro = controller.autenticar(txUsuario.getText(), senha);

        if (erro != null) {
            JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        dispose();
        new Menu();
    }
}
