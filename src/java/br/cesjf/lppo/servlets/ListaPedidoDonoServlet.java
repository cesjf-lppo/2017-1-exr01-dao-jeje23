/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lppo.servlets;

import br.cesjf.lppo.Pedido;
import br.cesjf.lppo.dao.PedidoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alunoces
 */
@WebServlet(name = "ListaPedidoDonoServlet", urlPatterns = {"/lista-pedido-dono.html"})
public class ListaPedidoDonoServlet extends HttpServlet {

    
    
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         List<Pedido> pedidos;
          String filtro = request.getParameter("dono");
          float total = 0;
        
        try {
            PedidoDAO dao = new PedidoDAO();
            pedidos = dao.getByDono(filtro);
            total = dao.calculaTotalDono(filtro);
            
        } catch (Exception ex) {
            Logger.getLogger(ListaPedidoServlet.class.getName()).log(Level.SEVERE, null, ex);
            pedidos = new ArrayList<>();
            request.setAttribute("mensagem", ex.getLocalizedMessage());
        }
        
        
        request.setAttribute("pedidos" , pedidos);
        request.setAttribute("total" , total);
        request.getRequestDispatcher("/WEB-INF/lista-pedido-dono.jsp").forward(request, response);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

   
    

}
