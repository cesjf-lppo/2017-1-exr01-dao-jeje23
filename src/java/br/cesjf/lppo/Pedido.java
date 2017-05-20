
package br.cesjf.lppo;

import java.util.Date;


public class Pedido {
    private Long id;
    private int pedido;
    private String dono;
    private double valor;
    private String nome;
    private Date atualizacao;
    
    
    public Pedido(){
      
    }

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public int getPedido() {
        return pedido;
    }

   
    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

   
    public String getDono() {
        return dono;
    }

    
    public void setDono(String dono) {
        this.dono = dono;
    }

   
    public double getValor() {
        return valor;
    }

   
    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public Date getAtualizacao() {
        return atualizacao;
    }

 
    public void setAtualizacao(Date atualizacao) {
        this.atualizacao = atualizacao;
    }
    
}
