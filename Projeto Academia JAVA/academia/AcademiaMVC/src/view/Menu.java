package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JFrame {

    public Menu() {
        setSize(800, 600);
        setTitle("Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);

        JMenu alunos = new JMenu("Alunos");
        JMenu prof = new JMenu("Professores");
        JMenu outros = new JMenu("Outros");
        JMenu sair = new JMenu("Sair");

        JMenuItem aluCad = new JMenuItem("Alunos Cadastrados");
        JMenuItem cadAluno = new JMenuItem("Cadastrar Aluno");
        JMenuItem profCad = new JMenuItem("Professores Cadastrados");
        JMenuItem cadProf = new JMenuItem("Cadastrar Professor");
        JMenuItem planos = new JMenuItem("Planos");
        JMenuItem aulas = new JMenuItem("Aulas");
        JMenuItem confSair = new JMenuItem("Confirmar Saida");

        alunos.add(aluCad);
        alunos.add(cadAluno);
        prof.add(profCad);
        prof.add(cadProf);
        outros.add(planos);
        outros.add(aulas);
        sair.add(confSair);

        cadAluno.addActionListener(e -> new CadastrarAluno());
        aluCad.addActionListener(e -> new AlunosMatriculados());
        cadProf.addActionListener(e -> new CadastrarInstrutor());
        profCad.addActionListener(e -> new InstrutoresCadastrados());
        planos.addActionListener(e -> new CadastrarPlano());
        confSair.addActionListener(e -> System.exit(0));

        JMenuBar bar = new JMenuBar();
        bar.add(alunos);
        bar.add(prof);
        bar.add(outros);
        bar.add(sair);
        setJMenuBar(bar);

        setVisible(true);
    }
}