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
@WebServlet(name = "EditNewsController", urlPatterns = {"/EditNewsController"})
public class EditNewsController extends HttpServlet {

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

        NewsDAO dao = new NewsDAO();
        NewsTypeDAO newsTypeDao = new NewsTypeDAO();
        News product = dao.getNewsById(id);
        List<NewsType> listC = newsTypeDao.getAllCategory();

        String select = "";

        for (NewsType category : listC) {
            if (product.getNewsType() != category.getId()) {
                select += "<option value=\"" + category.getId() + "\">" + category.getNewsType() + "</option>\n";
            } else {
                select += "<option value=\"" + category.getId() + "\"selected>" + category.getNewsType() + "</option>\n";
            }
        }

        PrintWriter out = response.getWriter();
        out.println("<div class=\"modal-dialog\">\r\n"
        		+ "                    <div class=\"modal-content\">\r\n"
        		+ "                        <form action=\"EditNewsController\" method=\"post\">\r\n"
        		+ "                            <div class=\"modal-header\">\r\n"
        		+ "                                <h4 class=\"modal-title\">Edit news</h4>\r\n"
        		+ "                                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\r\n"
        		+ "                            </div>\r\n"
        		+ "                            <div class=\"modal-body\">\r\n"
        		+ "                                <div class=\"form-group\">\r\n"
        		+ "                               		<input name=\"pID\" type=\"hidden\" class=\"form-control\" value=\""+product.getId()+"\">\r\n"
        		+ "                                    <label>Title</label>\r\n"
        		+ "                                    <input name=\"title\" type=\"text\" class=\"form-control\" required value=\""+product.getNewsTitle()+"\">\r\n"
        		+ "                                </div>\r\n"
        		+ "                                <div class=\"form-group\">\r\n"
        		+ "                                    <label>Thumbnail</label>\r\n"
        		+ "                                    <input name=\"image\" type=\"text\" class=\"form-control\" required value=\""+product.getImage()+"\">\r\n"
        		+ "                                </div>\r\n"
        		+ "                                <div class=\"form-group\">\r\n"
        		+ "                                    <label>Content</label>\r\n"
        		+ "                                    <textarea id=\"content2\" name=\"content\" class=\"form-control\">"+product.getNewsContent()+"</textarea>\r\n"
        		+ "                                </div>\r\n"
        		+ "\r\n"
        		+ "                                <div class=\"form-group\">\r\n"
        		+ "                                    <label>News Type</label>\r\n"
        		+ "                                    <select name=\"newsType\" class=\"form-select\" aria-label=\"Default select example\">\r\n"
        		+ select
        		+ "                                    </select>\r\n"
        		+ "                                </div>\r\n"
        		+ "\r\n"
        		+ "                            </div>\r\n"
        		+ "                            <div class=\"modal-footer\">\r\n"
        		+ "                                <input type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\" value=\"Cancel\">\r\n"
        		+ "                                <input type=\"submit\" class=\"btn btn-success\" value=\"Save\">\r\n"
        		+ "                            </div>\r\n"
        		+ "                        </form>\r\n"
        		+ "                    </div>\r\n"
        		+ "                </div>");
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
        String title = request.getParameter("title");
        String image = request.getParameter("image");
        String content = request.getParameter("content");
        String newsType = request.getParameter("newsType");

        NewsDAO dao = new NewsDAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if(a != null) {
        	News n = new News(title, content, Integer.parseInt(newsType), a.getId(), Date.valueOf(LocalDate.now()), image);
        	dao.updateNews(n, Integer.parseInt(nId));
        	request.getRequestDispatcher("ManagerControl").forward(request, response);
        }else {
        	response.sendRedirect("Login.jsp");
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
