package service;

import dao.ActionDAO;
import dao.AdminDAO;
import dao.ProductDAO;

public abstract class ServiceAbstract {

    public ProductDAO productDAO=new ProductDAO();
    public AdminDAO adminDAO=new AdminDAO();
    public ActionDAO actionDAO=new ActionDAO();
    
}
