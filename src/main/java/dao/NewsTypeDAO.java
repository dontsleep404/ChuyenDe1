package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.News;
import model.NewsType;

public class NewsTypeDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<NewsType> getAllCategory() {
        List<NewsType> list = new ArrayList<>();
        String query = "select * from newstype";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new NewsType(rs.getInt(1), rs.getString(2)));
            }

        } catch (Exception e) {
        }
        return list;
    }
    
    public void addNewNewsType(NewsType newsType) {
        String query = "INSERT into NewsType (newsType)\n"
                + "VALUES (?)";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, newsType.getNewsType());
            ps.executeUpdate();
        } catch (Exception e) {
        	System.out.println("error");
        }
    }
    
    public void updateNewsType(NewsType news, int nId) {
        String query = "UPDATE NewsType set newsType = ? where id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, news.getNewsType());
            ps.setInt(2, nId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public NewsType getNewsTypeByID(String id) {
        String query = "select * from NewsType where id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, id);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return new NewsType(rs.getInt(1),
                        rs.getString(2));
            }

        } catch (Exception e) {
        }
        return null;
    }
    
    public void deleteNewsType(int pid) {
        String query = "DELETE FROM NewsType "
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
