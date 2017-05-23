<%-- 
    Document   : lista-pedido
    Created on : 08/05/2017, 22:12:55
    Author     : alunoces
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Pedido Dono</title>
    </head>
    <body>
        <%@include file="jspf/menu.jspf" %>
        <h1>Lista Pedido Dono</h1>
         <table border="1">
            
            <tr>
                <th>ID</th>
                <th>Pedido</th>
                <th>Dono</th>
                <th>Valor</th>
                <th>Nome</th>
                <th>Atualizacao</th>
                
            </tr>
            <c:forEach var="pedido" items="${pedidos}">
                <tr>
                <td>${pedido.id} </a></td> 
                <td>${pedido.pedido}</td> 
                <td>${pedido.dono}</td> 
                <td>${pedido.valor}</td>
                <td>${pedido.nome}</td>
                <td>${pedido.atualizacao}</td>
                </tr>
            </c:forEach>
            <tr>
                    <td colspan="5"> Total</td>
                    <td>${total}</td>
                </tr>
        
        
    </body>
</html>

