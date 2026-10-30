package model;
 
public class Plano {
    private int id;
    private String nomePlano;
    private double preco;
    private int duracao;
    private String intervaloPagamento; // Mensal, Trimestral, Anual...
 
    public Plano(String nomePlano, double preco, int duracao, String intervaloPagamento) {
        this.nomePlano = nomePlano;
        this.preco = preco;
        this.duracao = duracao;
        this.intervaloPagamento = intervaloPagamento;
    }
 
    // id vem do banco (idPlano). Preenchido pelo DAO ao carregar do MySQL.
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
 
    public String getNomePlano() { return nomePlano; }
    public void setNomePlano(String nomePlano) { this.nomePlano = nomePlano; }
 
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
 
    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) { this.duracao = duracao; }
 
    public String getIntervaloPagamento() { return intervaloPagamento; }
    public void setIntervaloPagamento(String intervaloPagamento) { this.intervaloPagamento = intervaloPagamento; }
 
    @Override
    public String toString() {
        return nomePlano + " (R$ " + preco + " - " + intervaloPagamento + ")";
    }
}
 