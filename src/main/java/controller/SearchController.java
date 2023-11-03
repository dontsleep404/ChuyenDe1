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

import com.google.gson.JsonObject;

import dao.NewsDAO;
import dao.NewsTypeDAO;

/**
 * Servlet implementation class SearchController
 */
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public int getPageSize(int numberProduct, int allProduct) {
        int pageSize = allProduct / numberProduct;
        if (allProduct % numberProduct != 0) {
            pageSize = (allProduct / numberProduct) + 1;
        }
        return pageSize;

    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        
        String txtSearch = request.getParameter("txt");
        String newsTypeId = request.getParameter("newsTypeId");
        int pageSize = getPageSize(6, new NewsDAO().search(txtSearch,newsTypeId).size());
		String index = request.getParameter("pageIndex");
        int pageIndex = 0;
        if (index == null) {
            pageIndex = 1;
        } else {
            pageIndex = Integer.parseInt(index);
        }
        List<News> list = new NewsDAO().searchWithPaging(txtSearch, pageIndex, 6,newsTypeId);

        PrintWriter out = response.getWriter();
        String product="";
        for (News o : list) {
        	product +="					<div class=\"col-12 col-sm-6 col-md-12 col-xl-4\">\r\n"
        			+ "						<div class=\"single-product-wrapper\">\r\n"
        			+ "							<div class=\"product-img\">\r\n"
        			+ "								<img src=\""+o.getImage()+"\" alt=\"\">\r\n"
        			+ "							</div>\r\n"
        			+ "\r\n"
        			+ "							<div\r\n"
        			+ "								class=\"product-description d-flex align-items-center justify-content-between\">\r\n"
        			+ "								<div class=\"product-meta-data\">\r\n"
        			+ "									<div class=\"line\"></div>\r\n"
        			+ "									\r\n"
        			+ "									<a href=\"NewsDetailController?newsID="+o.getId()+"\">\r\n"
        			+ "										<h6>"+o.getNewsTitle()+"</h6>\r\n"
        			+ "									</a>\r\n"
        			+ "								</div>\r\n"
        			+ "\r\n"
        			+ "								<div class=\"ratings-cart text-right\">\r\n"
        			+ "								\r\n"
        			+ "									<div class=\"cart\" >\r\n"
        			+ "										\r\n"
        			+ "										<a href=\"Home.jsp\"\r\n"
        			+ "											style='font-size: 24px;' title=\"Add to favourite\">&#129505;</a>\r\n"
        			+ "									</div>\r\n"
        			+ "								</div>\r\n"
        			+ "							</div>\r\n"
        			+ "						</div>\r\n"
        			+ "					</div>";
        }
        
        String strPaging = "";
        String active ="";
        for (int i = 1; i <= pageSize; i++) {
        	if(pageIndex == i) {
        		active = "active";
        	}else {
        		active = "";
        	}
			strPaging += "<li class=\"page-item "+active+"\"><a\r\n"
					+ "									class=\"page-link\"\r\n"
					+ "									href=\"ListController?pageIndex="+i+"&txtSearch="+txtSearch+"&newsTypeId="+newsTypeId+"\">"+i+"</a></li>";
		}
        
        String newsTypeMenu = "";
        List<NewsType> lsNewsType = new NewsTypeDAO().getAllCategory();
        int id = (newsTypeId == null || newsTypeId.trim().equals("")) ? lsNewsType.get(0).getId() : Integer.parseInt(newsTypeId);
        for (NewsType newsType : lsNewsType) {
			if(newsType.getId() == id) {
				active = "active";
        	}else {
        		active = "";
        	}
			newsTypeMenu+= "<li class=\""+active+"\"><a href=\"ListController?pageIndex=1&txtSearch="+txtSearch+"&newsTypeId="+newsType.getId()+"\">"+newsType.getNewsType()+"</a></li>";
		}
        
        /* construct your json */
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("product", product);
        jsonResponse.addProperty("paging", strPaging);            
        jsonResponse.addProperty("newsTypeMenu", newsTypeMenu); 
        /* send to the client the JSON string */
        response.getWriter().write(jsonResponse.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
