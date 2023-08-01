package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Admin;
import utility.DatabaseUtil;

public class AdminDAO {
    public boolean updateUsername(String username){
        boolean flag=false;
        try{
             Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE admin SET username=?");
            stmt.setString(1, username);
            int f=stmt.executeUpdate();
            flag=f>0?true:false;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean changePassword(String password){
        boolean flag=false;
        try{
             Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE admin SET password=?");
            stmt.setString(1, password);
            int f=stmt.executeUpdate();
            flag=f>0?true:false;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean auth(Admin admin){
        boolean flag=false;
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM admin");
            ResultSet res=stmt.executeQuery();
            res.next();
            Admin authend=new Admin(res.getString("username"),res.getString("password"));
            flag=authend.equals(admin);
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public Admin showUser(){
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM admin");
            ResultSet res=stmt.executeQuery();
            res.next();
            Admin authend=new Admin(res.getString("username"),res.getString("password"));
            return authend;
        }catch(Exception e){
            e.printStackTrace();
            return new Admin();
        }
    }
}
