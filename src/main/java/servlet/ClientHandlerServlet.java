package servlet;

import java.io.*;
import java.util.List;
import java.util.Objects;

import entities.Client;
import jakarta.servlet.ServletException;
import persistence.PersistenceHibernate;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "action", value = "/action")
public class ClientHandlerServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final PersistenceHibernate ph = new PersistenceHibernate();
        final String function = request.getParameter("function");
        final String id = request.getParameter("id");
        final String name = request.getParameter("name");
        final String city = request.getParameter("city");
        List<Client> clients = ph.getClientList();
        if (!Objects.equals(function, null)) {
            switch (function) {
                case "registerClient":
                    ph.registerClient(name, city);
                    clients = ph.getClientList();
                    break;
                case "findClientById":
                    clients.clear();
                    clients.add(ph.findClientById(Integer.parseInt(id)));
                    break;
                case "findClientByCity":
                    clients = ph.findClientByCity(city);
                    break;
                case "changeClientCity":
                    ph.changeClientCity(Integer.parseInt(id), city);
                    break;
                case "cleanFilters":
                    clients = ph.getClientList();
                    break;
            }
        }
        StringBuilder clientList = new StringBuilder();
        for (Client c : clients) {
            clientList.append(c);
        }
        request.setAttribute("clients", clientList.toString());
        request.setAttribute("nbClients", clients.size());
        request.getRequestDispatcher("endpoints/clientsListMenu.jsp").forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}
