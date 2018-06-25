package dao;

import entity.Category;
import util.DBUtil;

import java.security.interfaces.RSAKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public int getTotal(){
        int total = 0;
        try(Connection c = DBUtil.getConnection(); Statement s = ((Connection) c).createStatement()){
            ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM category");
            while(rs.next()){
                total = rs.getInt("1");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    public void add(Category category){
        String sql = "INSERT INTO category(id,name) VALUES(null,?)";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,category.getName());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                category.setId(rs.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(Category category){
        String sql ="UPDATE category set name=? WHERE id = ?";
        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,category.getName());
            ps.setInt(2,category.getId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int id){
        String sql = "DELETE FROM category WHERE id =?";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Category get(int id){
        String sql = "SELECT * FROM category WHERE id = ?";
        Category category = null;
        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return category;
    }

    public List<Category> list ( int start , int count){
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category ORDER BY id DESC LIMIT ?,?";
        Category category = new Category();
        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }

    public List<Category> list(){
        return list(0,Short.MAX_VALUE);
    }
}
