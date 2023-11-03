package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import context.DBContext;

import java.util.ArrayList;
import model.News;

public class NewsDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<News> search(String txtSearch, String newsType){
		List<News> list = new ArrayList<>();
		String query = "select * from news";
		if(newsType != null && !newsType.trim().equals("") && txtSearch != null && !txtSearch.trim().equals("")) {
			query += " where newsTitle like ? and newsType = ?";
		}else if(txtSearch != null && !txtSearch.trim().equals("")) {
			query += " where newsTitle like ?";
		}else if(newsType != null && !newsType.trim().equals("")) {
			query += " where newsType = ?";
		}
       
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
    		if(newsType != null && !newsType.trim().equals("") && txtSearch != null && !txtSearch.trim().equals("")) {
    			ps.setString(1, "%" + txtSearch + "%");
    			ps.setInt(2, Integer.parseInt(newsType));
    		}else if(txtSearch != null && !txtSearch.trim().equals("")) {
    			ps.setString(1, "%" + txtSearch + "%");
    		}else if(newsType != null && !newsType.trim().equals("")) {
    			ps.setInt(1, Integer.parseInt(newsType));
    		}
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new News(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(7)));
            }

        } catch (Exception e) {
        }
        return list;
	}
    
	public List<News> searchWithPaging(String txtSearch,int pageIndex, int pageSize, String newsType){
		List<News> list = new ArrayList<>();
		String query = "select * from news";
		if(newsType != null && !newsType.trim().equals("") && txtSearch != null && !txtSearch.trim().equals("")) {
			query += " where newsTitle like ? and newsType = ?";
		}else if(txtSearch != null && !txtSearch.trim().equals("")) {
			query += " where newsTitle like ?";
		}else if(newsType != null && !newsType.trim().equals("")) {
			query += " where newsType = ?";
		}
		query += " LIMIT ? OFFSET ?";
       
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
    		if(newsType != null && !newsType.trim().equals("") && txtSearch != null && !txtSearch.trim().equals("")) {
    			ps.setString(1, "%" + txtSearch + "%");
    			ps.setInt(2, Integer.parseInt(newsType));
           	 	ps.setInt(3, pageSize);
                ps.setInt(4, pageIndex * pageSize - pageSize);
    		}else if(txtSearch != null && !txtSearch.trim().equals("")) {
           	 	ps.setString(1, "%" + txtSearch + "%");
        	 
           	 	ps.setInt(2, pageSize);
                ps.setInt(3, pageIndex * pageSize - pageSize);
    		}else if(newsType != null && !newsType.trim().equals("")) {
    			ps.setInt(1, Integer.parseInt(newsType));
        	 
           	 	ps.setInt(2, pageSize);
                ps.setInt(3, pageIndex * pageSize - pageSize);
    		}else {
            	ps.setInt(1, pageSize);
                ps.setInt(2, pageIndex * pageSize - pageSize);
              
            }
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new News(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(7)));
            }

        } catch (Exception e) {
        }
        return list;
	}
	
	public News getNewsById(String id) {
		String query = "select news.*, account.userName, newstype.newsType from news"
				+ " left join account on news.author = account.id"
				+ " left join newstype on newstype.id = news.newsType"
				+ " where news.id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, id);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return new News(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
            }

        } catch (Exception e) {
        }
        return null;
	}
    
    public void addNewNews(News news) {
        String query = "INSERT into News (newsTitle, newsContent, newsType, author, createDate, image)\n"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, news.getNewsTitle());
            ps.setString(2, news.getNewsContent());
            ps.setInt(3, news.getNewsType());
            ps.setInt(4, news.getAuthor());
            ps.setDate(5, news.getCreateDate());
            ps.setString(6, news.getImage());
            ps.executeUpdate();
        } catch (Exception e) {
        	System.out.println("error");
        }
    }
    
    public void updateNews(News news, int nId) {
        String query = "UPDATE News set newsTitle = ?, newsContent = ?, newsType = ?, author = ?, createDate = ?, image = ? where id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, news.getNewsTitle());
            ps.setString(2, news.getNewsContent());
            ps.setInt(3, news.getNewsType());
            ps.setInt(4, news.getAuthor());
            ps.setDate(5, news.getCreateDate());
            ps.setString(6, news.getImage());
            ps.setInt(7, nId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public News getNewsByID(String id) {
        String query = "select * from News where id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, id);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return new News(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(7));
            }

        } catch (Exception e) {
        }
        return null;
    }
    
    public void deleteNews(int pid) {
        String query = "DELETE FROM News "
                + "WHERE id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setInt(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
