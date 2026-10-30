package model;

public class AlunoCadastrado extends Pessoa {
    private Plano planoEscolhido;
    private Instrutor instrutorResponsavel;

    public AlunoCadastrado(String nome, String cpf, String email, String telefone,
                           String idade, String endereco, String sexo, String senha,
                           Plano plano, Instrutor instrutor) {
        super(nome, cpf, email, telefone, idade, endereco, sexo, senha);
        this.planoEscolhido = plano;
        this.instrutorResponsavel = instrutor;
    }

    public Plano getPlanoEscolhido() { return planoEscolhido; }
    public void setPlanoEscolhido(Plano planoEscolhido) { this.planoEscolhido = planoEscolhido; }

    public Instrutor getInstrutorResponsavel() { return instrutorResponsavel; }
    public void setInstrutorResponsavel(Instrutor instrutorResponsavel) { this.instrutorResponsavel = instrutorResponsavel; }
}
