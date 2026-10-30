package model;

public class Instrutor extends Pessoa {
    private String funcao;

    public Instrutor(String nome, String cpf, String email, String telefone,
                     String idade, String endereco, String sexo, String senha, String funcao) {
        super(nome, cpf, email, telefone, idade, endereco, sexo, senha);
        this.funcao = funcao;
    }

    public String getFuncao() { return funcao; }
    public void setFuncao(String funcao) { this.funcao = funcao; }

    @Override
    public String toString() {
        return getNome() + " (" + funcao + ")";
    }
}
