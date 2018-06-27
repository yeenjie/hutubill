package dao;

import entity.Record;
import util.DBUtil;
import util.DateUtil;

import java.security.interfaces.RSAKey;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class RecordDAO{

    public int getTotal(){
        int total=0;
        try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement()){
            ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM record");
            if(rs.next()){
                total = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    public Record get(int id){
        String sql ="SELECT * FROM record WHERE id = ?";
        Record record = null;
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                record.setCid(rs.getInt("cid"));
                record.setDate(rs.getDate("date"));
                record.setComment(rs.getString("comment"));
                record.setId(rs.getInt("id"));
                record.setSpend(rs.getInt("spend"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  record;
    }

    public void add(Record record){
        String sql ="INSERT INTO record(id,spend,cid,comment,date) VALUES(null,?,?,?,?) ";
        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,record.getSpend());
            ps.setInt(2,record.getCid());
            ps.setString(3,record.getComment());
            ps.setDate(4,DateUtil.util2sql(record.getDate()));
            ps.execute();
            ResultSet rs= ps.getGeneratedKeys();
            if(rs.next()){
                record.setId(rs.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update (Record record){
        String sql="UPDATE record SET spend=?,cid=?,comment=?,date=? where id=?";
        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,record.getSpend());
            ps.setInt(2,record.getCid());
            ps.setString(3,record.getComment());
            ps.setDate(4,DateUtil.util2sql(record.getDate()));
            ps.setInt(5,record.getId());
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int id){
        String sql = "DELETE FROM record WHERE id = ?";
        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Record> list(int start , int count){
        List<Record> records = new ArrayList<>();
        String sql = "SELECT * FROM record ORDER BY id DESC LIMIT ?,?";
        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Record record = new Record();
                record.setId(rs.getInt("id"));
                record.setSpend(rs.getInt("spend"));
                record.setComment(rs.getString("comment"));
                record.setDate(rs.getDate("date"));
                record.setCid(rs.getInt("cid"));
                records.add(record);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return records;
    }
    public List<Record> list(){
        return list(0,Short.MAX_VALUE);
    }
    public List<Record> list(int cid){
        List<Record> records = new ArrayList<Record>();
        String sql = "SELECT * FROM record WHERE  cid = ?";
        try(Connection c = DBUtil.getConnection();PreparedStatement ps= c.prepareStatement(sql)){
            ps.setInt(1,cid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Record record = new Record();
                record.setCid(rs.getInt("id"));
                record.setDate(rs.getDate("date"));
                record.setComment(rs.getString("string"));
                record.setSpend(rs.getInt("spend"));
                record.setId(rs.getInt("id"));
                records.add(record);

            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return records;
    }

    public List<Record> list(Date day){
        List<Record> records =new ArrayList<Record>();
        String sql = "SELECT * FROM record WHERE date=?";
        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setDate(1,DateUtil.util2sql(day));
            ResultSet rs = ps.executeQuery();
            setRecordValues(records, rs);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return records;
    }
    public List<Record> listToday(){
        return list(DateUtil.today());
    }

    public List<Record> listThisMonth(){
        return list(DateUtil.monthBegin(),DateUtil.monthEnd());
    }

    public List<Record> list(Date start,Date end){
        List<Record> records =new ArrayList<Record>();
        String sql = "SELECT * FROM record WHERE date >= ? AND date <= ?";
        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql)){
            ps.setDate(1,DateUtil.util2sql(start));
            ps.setDate(2,DateUtil.util2sql(end));
            ResultSet rs = ps.executeQuery();
            setRecordValues(records, rs);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return  records;
    }

    private void setRecordValues(List<Record> records, ResultSet rs) throws SQLException {
        while(rs.next()){
            Record record = new Record();
            record.setId(rs.getInt("id"));
            record.setSpend(rs.getInt("spend"));
            record.setComment(rs.getString("comment"));
            record.setCid(rs.getInt("cid"));
            record.setDate(rs.getDate("date"));
            records.add(record);
        }
    }

}
