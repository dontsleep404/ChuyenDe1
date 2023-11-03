package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.News;
import model.NewsType;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.NewsDAO;
import dao.NewsTypeDAO;
/**
 * Servlet implementation class ListController
 */
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try (PrintWriter out = response.getWriter()){
			
			String txtSearch = request.getParameter("txtSearch");
			String newsTypeId = request.getParameter("newsTypeId");
			int pageSize = getPageSize(6, new NewsDAO().search(txtSearch,newsTypeId).size());
			String index = request.getParameter("pageIndex");
	        int pageIndex = 0;
	        if (index == null) {
	            pageIndex = 1;
	        } else {
	            pageIndex = Integer.parseInt(index);
	        }
			
			List<News> ls = new NewsDAO().searchWithPaging(txtSearch, pageIndex, 6,newsTypeId);
			
			request.setAttribute("totalPage", pageSize);
	        request.setAttribute("numberProduct", 6);
	        request.setAttribute("pageIndex", pageIndex);
	        request.setAttribute("searchValue", txtSearch);
	        request.setAttribute("newsTypeValue", newsTypeId);
	        request.setAttribute("listP", ls);
	        
	        List<NewsType> lsNewsType = new NewsTypeDAO().getAllCategory();
	        request.setAttribute("listC", lsNewsType);
	        
	        request.setAttribute("tag", newsTypeId == null ? lsNewsType.get(0).getId():Integer.parseInt(newsTypeId));
	        
			request.getRequestDispatcher("Home.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public int getPageSize(int numberProduct, int allProduct) {
        int pageSize = allProduct / numberProduct;
        if (allProduct % numberProduct != 0) {
            pageSize = (allProduct / numberProduct) + 1;
        }
        return pageSize;

    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
