
package br.cesjf.lppo.servlets;


import br.cesjf.lppo.Pedido;
import br.cesjf.lppo.dao.PedidoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "AdicionarPedidoServlet", urlPatterns = {"/novo.html"})
public class AdicionarPedidoServlet extends HttpServlet {


    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.getRequestDispatcher("WEB-INF/novo-pedido.jsp").forward(request, response);
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Pedido novoPedido = new Pedido();
        novoPedido.setPedido(Integer.parseInt(request.getParameter("pedido")));
        novoPedido.setDono(request.getParameter("dono"));
        novoPedido.setValor(Float.parseFloat(request.getParameter("valor")));
        novoPedido.setNome(request.getParameter("nome"));
        
        
        try{
            PedidoDAO dao = new PedidoDAO();
        dao.cria(novoPedido);
        } catch(Exception ex){
            System.err.println(ex);
            request.setAttribute("mensagem", ex);
            request.getRequestDispatcher("WEB-INF/novo-contato.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("pedidos.html");
    }

 
}
 


