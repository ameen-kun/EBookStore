package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Product;
import utility.DatabaseUtil;

public class ProductDAO {

    public boolean addProduct(Product product) {
        boolean flag=false;
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO product(product_name,product_manfdate,product_stock,product_price,product_category) VALUES (?, ?, ?, ?, ?)");
                stmt.setString(1, product.getProd_name());
                stmt.setDate(2, new Date(product.getManf_date().getTime()));
                stmt.setInt(3,product.getStock());
                stmt.setDouble(4, product.getPrice());
                stmt.setString(5,product.getCategory());
                int f=stmt.executeUpdate();
                flag=(f>0)?true:false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean increaseStock(int id, int by){
        boolean flag=false;
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE product SET product_stock = product_stock + ?  WHERE product_id =?");
                stmt.setInt(1, by);
                stmt.setInt(2,id);
                int f=stmt.executeUpdate();
                flag=(f>0?true:false);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean decreaseStock(int id,int by){
        boolean flag=false;
         try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE product SET product_stock = product_stock - ?  WHERE product_id =?");
                stmt.setInt(1, by);
                stmt.setInt(2,id);
                int f=stmt.executeUpdate();
                flag=(f>0?true:false);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateStock(int id,int stock){
        boolean flag=false; 
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE product SET product_stock = ? WHERE product_id =?");
                stmt.setInt(1, stock);
                stmt.setInt(2,id);
                int f=stmt.executeUpdate();
                flag=(f>0?true:false);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean deleteProduct(int id){
        boolean flag=false;
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM product where product_id=?");
            stmt.setInt(1,id);
            int f=stmt.executeUpdate();
            flag=(f>0?true:false);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<Product> getProducts(){
        List<Product> allProducts=new LinkedList<>();
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM product");
            ResultSet res=stmt.executeQuery();
            while(res.next()){
                allProducts.add(new Product(res.getInt("product_id"),res.getString("product_name"), res.getInt("product_stock"), res.getDate("product_manfdate"), res.getString("product_category"),res.getDouble("product_price")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return allProducts;
    }

    public Product getProduct(int id){
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM product WHERE product_id=?");
            stmt.setInt(1,id);
            ResultSet res=stmt.executeQuery();
            res.next();
            return new Product(res.getInt("product_id"),res.getString("product_name"), res.getInt("product_stock"), res.getDate("product_manfdate"), res.getString("product_category"),res.getDouble("product_price"));
        }catch (SQLException e) {
            e.printStackTrace();
            return new Product();
        }
    }

    public List<Product> searchByName(String name){
        List<Product> filteredProducts=new LinkedList<>();
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM product WHERE LOWER(product_name) LIKE ?");
            stmt.setString(1, "%"+name+"%");
            ResultSet res=stmt.executeQuery();
            while(res.next()){
                filteredProducts.add(new Product(res.getInt("product_id"),res.getString("product_name"), res.getInt("product_stock"), res.getDate("product_manfdate"), res.getString("product_category"),res.getDouble("product_price")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredProducts;
    }

    public List<Product> searchByCategory(String name){
        List<Product> filteredProducts=new LinkedList<>();
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM product WHERE LOWER(product_category) LIKE ?");
            stmt.setString(1, "%"+name+"%");
            ResultSet res=stmt.executeQuery();
            while(res.next()){
                filteredProducts.add(new Product(res.getInt("product_id"),res.getString("product_name"), res.getInt("product_stock"), res.getDate("product_manfdate"), res.getString("product_category"),res.getDouble("product_price")));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredProducts;
    }

    public boolean clearAllProducts(){
        boolean flag=false;
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM product");
            int f=stmt.executeUpdate();
            flag=(f>0)?true:false;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
