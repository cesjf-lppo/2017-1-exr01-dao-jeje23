
package br.cesjf.lppo.dao;

import br.cesjf.lppo.Pedido;
import java.sql.Connection;
import java.sql.Date;
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
     private final PreparedStatement opTotalPedidoDono;
     private final PreparedStatement opBuscaPorId;
     private final PreparedStatement opAtualiza;
     
     
     public PedidoDAO ()throws Exception{
            Connection conexao = ConnectionFactory.createConnection();
                
                opNovo  = conexao.prepareStatement("INSERT INTO Pedido (pedido, dono, valor, nome) VALUES(?, ?, ?, ?)");
                opListar = conexao.prepareStatement("SELECT * FROM Pedido");
                opListarPedido = conexao.prepareStatement("SELECT * FROM Pedido WHERE pedido = ?");
                opTotalPedido = conexao.prepareStatement("SELECT SUM (valor) AS total FROM Pedido WHERE pedido = ?");
                opListarPedidoDono = conexao.prepareStatement("SELECT * FROM Pedido WHERE dono = ?");
                opTotalPedidoDono = conexao.prepareStatement("SELECT SUM (valor) AS total FROM Pedido WHERE dono = ?");
                opBuscaPorId = conexao.prepareStatement("SELECT * FROM Pedido WHERE id=?");
                opAtualiza = conexao.prepareStatement("UPDATE Pedido SET pedido = ?, dono = ?, valor = ?, nome = ?, atualizacao = CURRENT_TIMESTAMP WHERE id = ?");
                
               
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
            opListarPedidoDono.setString(1, dono);
            ResultSet resultado = opListarPedidoDono.executeQuery();
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
         public float calculaTotalDono(String dono) throws Exception{
            try{
                float valor = 0;
                opTotalPedidoDono.setString(1, dono);
                ResultSet resultado = opTotalPedidoDono.executeQuery();
                while(resultado.next()){
                   valor = resultado.getFloat(1);
                }
                return valor;
            }catch (SQLException ex){
                   throw new Exception("Erro ao buscar contatos no banco", ex);
            }
    }
       public Pedido getById(Long id)  throws Exception{
        try {
            Pedido pedido = null;
            opBuscaPorId.clearParameters();
            opBuscaPorId.setLong(1, id);
            ResultSet resultado = opBuscaPorId.executeQuery();
                if (resultado.next()){
                    pedido = new Pedido();
                    pedido.setId(resultado.getLong("id"));
                    pedido.setPedido(resultado.getInt("pedido"));
                    pedido.setDono(resultado.getString("dono"));
                    pedido.setValor(resultado.getDouble("valor"));
                    pedido.setNome(resultado.getString("nome"));
                    pedido.setAtualizacao(resultado.getDate("atualizacao"));
            }
            return pedido;
        } catch (SQLException ex){
            throw new Exception("Erro ao buscar contatos no banco", ex);
        }
    }
        public void atualiza(Pedido pedido) throws Exception {
        try{
            opAtualiza.clearParameters();
            opAtualiza.setInt(1, pedido.getPedido());
            opAtualiza.setString(2, pedido.getDono());
            opAtualiza.setDouble(3, pedido.getValor());
            opAtualiza.setString(4, pedido.getNome());  
            opAtualiza.setLong(5, pedido.getId());
            opAtualiza.executeUpdate();
        } catch (SQLException ex){
            throw new Exception("Erro ao inserir o contato", ex);
        }
       }
    }

