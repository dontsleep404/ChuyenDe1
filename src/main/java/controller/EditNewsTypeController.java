/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.News;
import model.NewsType;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "EditNewsTypeController", urlPatterns = {"/EditNewsTypeController"})
public class EditNewsTypeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        NewsTypeDAO dao = new NewsTypeDAO();
        NewsType product = dao.getNewsTypeByID(id);


        PrintWriter out = response.getWriter();
        out.println("<div class=\"modal-dialog\">\r\n"
        		+ "                    <div class=\"modal-content\">\r\n"
        		+ "                        <form action=\"EditNewsTypeController\" method=\"post\">\r\n"
        		+ "                            <div class=\"modal-header\">\r\n"
        		+ "                                <h4 class=\"modal-title\">Edit news type</h4>\r\n"
        		+ "                                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\r\n"
        		+ "                            </div>\r\n"
        		+ "                            <div class=\"modal-body\">\r\n"
        		+ "                                <div class=\"form-group\">\r\n"
        		+ "                                	<input name=\"pID\" type=\"hidden\" class=\"form-control\" required value=\""+product.getId()+"\">\r\n"
        		+ "                                    <label>News Type</label>\r\n"
        		+ "                                    <input name=\"newsType\" type=\"text\" class=\"form-control\" required value=\""+product.getNewsType()+"\">\r\n"
        		+ "                                </div>\r\n"
        		+ "                            </div>\r\n"
        		+ "                            <div class=\"modal-footer\">\r\n"
        		+ "                                <input type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\" value=\"Cancel\">\r\n"
        		+ "                                <input type=\"submit\" class=\"btn btn-success\" value=\"Save\">\r\n"
        		+ "                            </div>\r\n"
        		+ "                        </form>\r\n"
        		+ "                    </div>\r\n"
        		+ "                </div>     ");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String nId = request.getParameter("pID");
        String newsType = request.getParameter("newsType");

        NewsTypeDAO dao = new NewsTypeDAO();
		try {
			NewsType n = new NewsType(newsType);
			dao.updateNewsType(n, Integer.parseInt(nId));
			request.getRequestDispatcher("ManagerNewsTypeController").forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("ManagerNewsTypeController").forward(request, response);
			// TODO: handle exception
		}

        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
