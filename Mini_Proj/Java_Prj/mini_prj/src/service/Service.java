package service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import model.Action;
import model.Admin;
import model.Product;

public class Service extends ServiceAbstract {

    public void createAction(String type,int affecting,String change){
        actionDAO.createAction(new Action(type, affecting, change, LocalDateTime.now()));
    }

    public void addProduct(String name,int stock,Date date,String cat,double price){
        if(productDAO.addProduct(new Product(name,stock,date,cat,price))){
            createAction("PRODUCT_CREATION", -1, "PRODUCT ADDED WITH NAME "+name);
            System.out.println("PRODUCT ADDED");
        }
        else{
            System.out.println("PRODUCT ADDITION FAILED ");
        }
    }

    public void getAllProducts(){
        List<Product> prods=productDAO.getProducts();
        System.out.println("******\nPRODUCTS : ");
        if(prods.size()!=0)
        for(Product product:prods){
            System.out.println(product.toString());
        }
        else{
            System.out.println("NO PRODUCTS FOUND");
        }
    }

    public void getProductById(int id){
        Product prod=productDAO.getProduct(id);
        System.out.println(prod.toString());
    }

    public void filterProducts(int ch,String val){
        if(ch==1){
            List<Product> prods=productDAO.searchByName(val.toLowerCase());
            System.out.println("******\nSEARCH RESULT : ");
            if(prods.size()!=0)
            for(Product p:prods){
                System.out.println(p.toString());
            }
            else{
                System.out.println("NO RESULT");
            }
        }
        else if(ch==2){
            List<Product> prods=productDAO.searchByCategory(val.toLowerCase());
            System.out.println( "******\nSEARCH RESULT : ");
            if(prods.size()!=0)
            for(Product p:prods){
                System.out.println(p.toString());
            }
            else{
                System.out.println("NO RESULT");
            }
        }
        else{
            System.out.println("INVALID CHOICE ");
        }
    }

    public void deleteProductById(int ch,int id){
        boolean flag=false;
        switch(ch){
            case 1:
            flag=productDAO.deleteProduct(id);
            createAction("PRODUCT_DELETION", -1, "PRODUCT REMOVED WITH ID "+id);
            break;
            case 2:
            flag=productDAO.clearAllProducts();
            createAction("PRODUCT_DELETION",-1,"PRODUCTS_CLEARED");
            break;
            default:
            System.out.println("INVALID CHOICE");
            break;
        }
        if(flag){
            System.out.println("DELETE SUCCESSFUL ");
         }
        else{
            System.out.println("DELETE FAILED ");
        }
    }

    public void updateStock(int ch,int id,int val){
        boolean flag=false;
        switch(ch){
            case 1:
            flag=productDAO.increaseStock(id, val);
            createAction("PRODUCT_UPDATION", id, "STOCK_INCREASED BY "+val+" FOR PRODUCT WITH ID "+id);
            break;
            case 2:
            flag=productDAO.decreaseStock(id, val);
            createAction("PRODUCT_UPDATION", id, "STOCK DECREASED BY "+val+" FOR PRODUCT WITH ID "+id);
            break;
            case 3:
            flag=productDAO.updateStock(id, val);
            createAction("PRODUCT_UPDATION", id, "STOCK SET TO "+val+" FOR PRODUCT WITH ID "+id);
            break;
            default:
            System.out.println("INVALID CHOICE");
            break;
        }
        if(flag){
            System.out.println("STOCK UPDATED ");
        }
        else{
            System.out.println("STOCK UPDATE FAILED ");
        }
    }

    public void getAllActions(){
        List<Action> acts=actionDAO.getActions();
        System.out.println("******\nACTIONS : ");
        if(acts.size()!=0)
        for(Action a:acts){
            System.out.println(a.toString());
        }
        else{
            System.out.println("NO ACTIONS PERFORMED YET");
        }
    }

    public void getParticularActions(int id){
        List<Action> acts=actionDAO.getTimeline(id);
        System.out.println("******\nACTIONS : ");
        if(acts.size()!=0)
        for(Action a:acts){
            System.out.println(a.toString());
        }
        else{
            System.out.println("NO ACTIONS PERFORMED YET");
        }
    }

    public void getUser(){
        Admin ad=adminDAO.showUser();
        System.out.println(ad.toString());
    }

    public void updateAdmin(int ch,String val){
        boolean flag=false;
        switch(ch){
            case 1:
            flag=adminDAO.updateUsername(val);
            createAction("ADMIN_UPDATION", -1, "USERNAME CHANGED");
            break;
            case 2:
            flag=adminDAO.changePassword(val);
            createAction("ADMIN_UPDATION", -1, "PASSWORD CHANGED");
            break;
            default:
            System.out.println("INVALID CHOICE");
            break;
        }
        if(flag){
            System.out.println("ADMIN UPDATE SUCCESSFUL");
        }else{
            System.out.println("ADMIN UPDATE FAILED");
        }
    }

    public boolean authenticate(String username,String password){
        createAction("ADMIN_LOGIN", -1,null);
        return adminDAO.auth(new Admin(username,password));
    }

    public boolean deauth(){
        createAction("ADMIN_LOGOFF",-1,null);
        return false;
    }
}
