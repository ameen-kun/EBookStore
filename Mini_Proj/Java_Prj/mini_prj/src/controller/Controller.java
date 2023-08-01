package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import service.Service;

public class Controller {
    
    public static void ProductInventoryUtil() throws ParseException{
        Service apiService=new Service();
        Scanner sc=new Scanner(System.in);
        boolean isLoggedIn=false;
        while(true){
            System.out.println("******\nHELLO! WELCOME TO PRODUCT INVENTORY SYSTEM");
            System.out.println("1. LOGIN\nPRESS ANY OTHER KEY TO EXIT\nENTER CHOICE :");
            int ch=sc.nextInt();
            if(ch==1){
                sc.nextLine();
                System.out.println("USERNAME :");
                String user=sc.nextLine();
                System.out.println("PASSWORD :");
                String pass=sc.nextLine();
                isLoggedIn=apiService.authenticate(user, pass);
                if(isLoggedIn){
                    System.out.println("LOGIN SUCCESSFUL");
                    while(isLoggedIn){
                        System.out.println("******\nMENU :\n1.PRODUCTS\n2.ACTIONS\n3.ADMIN\nPRESS ANY OTHER KEY TO LOGOFF\nENTER CHOICE :");
                        ch=sc.nextInt();
                        sc.nextLine();
                        if(ch==1){
                            boolean productsMenu=true;
                            while(productsMenu){
                                System.out.println("******\nPRODUCTS MENU :\n1.ADD PRODUCT\n2.UPDATE STOCK OF A PRODUCT\n3.DELETE A PRODUCT\n4.SEE A PARTICULAR PRODUCT\n5.SEE ALL PRODUCTS\n6.SEARCH PRODUCTS\nPRESS ANY OTHER KEY TO GO BACK\nENTER CHOICE :");
                                ch=sc.nextInt();
                                sc.nextLine();
                                if(ch==1){
                                    System.out.println("PRODUCT NAME :");
                                    String name=sc.nextLine();
                                    System.out.println("STOCK :");
                                    int stock=sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("MANUFACTURING DATE (YYYY-MM-DD) :");
                                    String dateString=sc.nextLine();
                                    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
                                    Date date=formatter.parse(dateString);
                                    System.out.println("CATEGORY :");
                                    String cat=sc.nextLine();
                                    System.out.println("PRICE :");
                                    double price=sc.nextDouble();
                                    sc.nextLine();
                                    apiService.addProduct(name, stock, date , cat, price);
                                }
                                else if(ch==2){
                                    System.out.println("PRODUCT ID :");
                                    int pid=sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("******\nMENU :\n1.INCREASE STOCK BY A VALUE\n2.DECREASE STOCK BY A VALUE\n3.SET STOCK VALUE\nENTER CHOICE :");
                                    ch=sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("ENTER VALUE :");
                                    int val=sc.nextInt();
                                    sc.nextLine();
                                    apiService.updateStock(ch, pid, val);
                                }
                                else if(ch==3){
                                    System.out.println("******\n1.DELETE A PRODUCT\n2.DELETE ALL PRODUCTS\nENTER CHOICE :");
                                    ch=sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("PRODUCT ID TO BE DELETED :");
                                    int pid=sc.nextInt();
                                    sc.nextLine();
                                    apiService.deleteProductById(ch, pid);
                                }
                                else if(ch==4){
                                    System.out.println("******\nPRODUCT ID :");
                                    int pid=sc.nextInt();
                                    sc.nextLine();
                                    apiService.getProductById(pid);
                                }
                                else if(ch==5){
                                    apiService.getAllProducts();
                                }
                                else if(ch==6){
                                    System.out.println("******\n1.SEARCH BY NAME\n2.SEARCH BY CATEGORY\nENTER CHOICE :");
                                    ch=sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("ENTER SEARCH STRING :");
                                    String searchString=sc.nextLine();
                                    apiService.filterProducts(ch, searchString);
                                }
                                else{
                                    productsMenu=false;
                                }
                            }
                        }
                        else if(ch==2){
                            boolean actionsMenu=true;
                            while(actionsMenu){
                                System.out.println("******\nACTIONS MENU :\n1.GENERATE REPORT\n2.SEE TIMELINE OF A PRODUCT\n3.SEE ADMIN ACTIONS\nPRESS ANY OTHER KEY TO GO BACK\nENTER CHOICE :");
                                ch=sc.nextInt();
                                sc.nextLine();
                                if(ch==1){
                                    apiService.getAllActions();
                                }
                                else if(ch==2){
                                    System.out.println("ENTER PRODUCT ID :");
                                    int pid=sc.nextInt();
                                    sc.nextLine();
                                    apiService.getParticularActions(pid);
                                }
                                else if(ch==3){
                                    apiService.getParticularActions(-1);
                                }
                                else{
                                    actionsMenu=false;
                                }
                            }
                        }
                        else if(ch==3){
                            boolean adminMenu=true;
                            while(adminMenu){
                                System.out.println("******\nADMIN MENU :\n1.SEE CREDENTIALS\n2.UPDATE CREDENTIALS\nPRESS ANY OTHER KEY TO GO BACK\nENTER CHOICE :");
                                ch=sc.nextInt();
                                sc.nextLine();
                                if(ch==1){
                                    apiService.getUser();
                                }
                                else if(ch==2){
                                    System.out.println("******\n1.UPDATE USERNAME\n2.UPDATE PASSWORD\nENTER CHOICE :");
                                    ch=sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("NEW CREDENTIAL :");
                                    String cred=sc.nextLine();
                                    apiService.updateAdmin(ch, cred);
                                }
                                else{
                                    adminMenu=false;
                                }
                            }
                        }
                        else{
                            isLoggedIn=apiService.deauth();
                        }
                    }
                }
                else{
                    System.out.println("INVALID CREDENTIALS");
                }
            }
            else{
                System.out.println("THANK YOU!");
                break;
            }
        }
        sc.close();
    }
}
