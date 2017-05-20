
package br.cesjf.lppo.dao;

import br.cesjf.lppo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PedidoDAO {
     private final PreparedStatement opNovo;
     private final PreparedStatement opListar;
     private final PreparedStatement opListarPedido;
     private final PreparedStatement opTotalPedido;
     private final PreparedStatement opListarPedidoDono;
     
     
     public PedidoDAO ()throws Exception{
            Connection conexao = ConnectionFactory.createConnection();
                
                opNovo  = conexao.prepareStatement("INSERT INTO pedido (pedido, dono, valor, nome) VALUES(?, ?, ?, ?)");
                opListar = conexao.prepareStatement("SELECT * FROM pedido");
                opListarPedido = conexao.prepareStatement("SELECT * FROM pedido WHERE pedido = ? ");
                opTotalPedido = conexao.prepareStatement("SELECT SUM (valor) AS total FROM pedido WHERE pedido = ?");
                opListarPedidoDono = conexao.prepareStatement("SELECT * FROM pedido WHERE dono = ? ");
               
     }
     
     public void cria(Pedido novoPedido) throws Exception {
        try{
        opNovo.clearParameters();
        opNovo.setInt(1, novoPedido.getPedido());
        opNovo.setString(2, novoPedido.getDono());
        opNovo.setDouble(3, novoPedido.getValor());
        opNovo.setString(4, novoPedido.getNome());
        
        opNovo.executeUpdate();
        
        }catch (Exception ex){
            throw new Exception("Erro ao inserir o pedido", ex);
        }
    }
     public List<Pedido> listAll() throws Exception{
        try {
            List<Pedido> pedidos = new ArrayList<>();
            
            
            ResultSet resultado = opListar.executeQuery();
                while(resultado.next()){
                    Pedido novoPedido = new Pedido();
                    novoPedido.setId(resultado.getLong("id"));
                    novoPedido.setPedido(resultado.getInt("pedido"));
                    novoPedido.setDono(resultado.getString("dono"));
                    novoPedido.setValor(resultado.getDouble("valor"));
                    novoPedido.setNome(resultado.getString("nome"));
                    novoPedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
                    
                    pedidos.add(novoPedido);
                }
            
            
            return pedidos;
        } catch (SQLException ex){
            throw new Exception("Erro ao listar pedidos no banco", ex);
        }
    }
     public List<Pedido> getByPedido(Long pedido)  throws Exception{
         
         try{
            List<Pedido> pedidos = new ArrayList<>();
            opListarPedido.setLong(1, pedido);
            ResultSet resultado = opListarPedido.executeQuery();
                while(resultado.next()){
                    Pedido novoPedido = new Pedido();
                    novoPedido.setId(resultado.getLong("id"));
                    novoPedido.setPedido(resultado.getInt("pedido"));
                    novoPedido.setDono(resultado.getString("dono"));
                    novoPedido.setValor(resultado.getDouble("valor"));
                    novoPedido.setNome(resultado.getString("nome"));
                    novoPedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
                    
                    pedidos.add(novoPedido);
                }
                    
                 return pedidos;
        } catch (SQLException ex){
            throw new Exception("Erro ao buscar contatos no banco", ex);
            
        }
    }
     public float calculaTotal(Long pedido) throws Exception{
     try{
         float valor = 0;
         opTotalPedido.setLong(1, pedido);
         ResultSet resultado = opTotalPedido.executeQuery();
         while(resultado.next()){
            valor = resultado.getFloat(1);
         }
         return valor;
     }catch (SQLException ex){
            throw new Exception("Erro ao buscar contatos no banco", ex);
     }
    }
      public List<Pedido> getByDono(String dono)  throws Exception{
         
         try{
            List<Pedido> pedidos = new ArrayList<>();
            opListarPedido.setString(1, dono);
            ResultSet resultado = opListarPedido.executeQuery();
                while(resultado.next()){
                    Pedido novoPedido = new Pedido();
                    novoPedido.setId(resultado.getLong("id"));
                    novoPedido.setPedido(resultado.getInt("pedido"));
                    novoPedido.setDono(resultado.getString("dono"));
                    novoPedido.setValor(resultado.getDouble("valor"));
                    novoPedido.setNome(resultado.getString("nome"));
                    novoPedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
                    
                    pedidos.add(novoPedido);
                }
                    
                 return pedidos;
        } catch (SQLException ex){
            throw new Exception("Erro ao buscar contatos no banco", ex);
            
        }
    }
}
