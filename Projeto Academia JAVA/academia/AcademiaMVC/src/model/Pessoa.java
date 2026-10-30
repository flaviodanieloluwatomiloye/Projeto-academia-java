package model;
 
/** Todo cadastro (aluno ou instrutor) tem estes dados. */
public abstract class Pessoa {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String idade;
    private String endereco;
    private String sexo;
    private String senha;
 
    public Pessoa(String nome, String cpf, String email, String telefone,
                  String idade, String endereco, String sexo, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
        this.endereco = endereco;
        this.sexo = sexo;
        this.senha = senha;
    }
 
    // id vem do banco (idAluno/idInstrutor). Preenchido pelo DAO ao carregar do MySQL.
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
 
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
 
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
 
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
 
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
 
    public String getIdade() { return idade; }
    public void setIdade(String idade) { this.idade = idade; }
 
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
 
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
 
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
 