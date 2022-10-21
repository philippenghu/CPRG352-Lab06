package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author Hu Peng
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        User user;
        if (action != null && action.equals("logout")) {
            session.invalidate();
            session = request.getSession();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

        if (session.getAttribute("user")!=null) {
            
            user = (User) session.getAttribute("user");

            request.setAttribute("username", user.getUsername());
            ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
            request.setAttribute("items", items);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        } else {
            
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> items;
        String item;
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if (action != null && action.equals("register")) {

            String username = request.getParameter("username");
            User user = new User(username);
            items = new ArrayList<>();
            session.setAttribute("user", user);
            session.setAttribute("items", items);
        } else if (action != null && action.equals("add")) {

            items = (ArrayList) session.getAttribute("items");
            if (items == null) {
                items = new ArrayList<>();
                String itemInput = request.getParameter("itemInput");
                items.add(itemInput);
                session.setAttribute("items", items);

            } else {
                String itemInput = request.getParameter("itemInput");
                items.add(itemInput);
                session.setAttribute("items", items);
             

            }

        } else if (action != null && action.equals("delete")) {
            items = (ArrayList) session.getAttribute("items");
            item = request.getParameter("itemR");
            System.out.println(item);
            items.remove(item);
            session.setAttribute("items", items);

        }
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        return;

    }

}
