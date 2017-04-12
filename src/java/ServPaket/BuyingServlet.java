package ServPaket;

import classPaket.DBengine;
import classPaket.Korisnik;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyingServlet extends HttpServlet {
        public static int userID;
        public static int userBL;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        DBengine.ListAllProduct();
        String userDetailsDecorater = "<div style=\"float:right; background-color:#99ccff; width:370px; font-family:cursive; opacity:0.7; -webkit-box-shadow: 20px 10px 20px 2px #0A060A; box-shadow: 20px 10px 20px 2px #0A060A;-webkit-border-radius: 14px 20px 60px 14px;border-radius: 14px 20px 60px 14px;\">";
        String bigDivDecorater = "<div style='clear:both;background-color:#99ccff; height:80px; font-family:cursive; font-size:40px; opacity:0.7; box-shadow: 00px 20px 20px 2px #0A060A;-webkit-border-radius: 20px 20px 20px 20px;'><marquee loop='-1' ;scrollamount='1' width='100%'>Our current offer:</marquee></div><br>";
        
        String currentUSER = request.getParameter("userNAME");
        String password = request.getParameter("passWORD");
        DBengine.ListSpecUser(currentUSER, password);
        List<Korisnik> userCHECK = DBengine.specKlist;
        userID = userCHECK.get(0).getId();
        userBL = userCHECK.get(0).getBalance();
        
        List<classPaket.Proizvod> products = DBengine.ProductList;
        
        if(currentUSER.equals(userCHECK.get(0).getUsername())&&password.equals(userCHECK.get(0).getPassword())){
                  
         try (PrintWriter out = response.getWriter()) {
     
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>BuyingServlet</title>");            
            out.println("</head>");
            out.println("<body background=\"http://www.easyfreepatterns.com/patterns/70/quilt-patterns-ipad-wallpaper-simple-pattern-with-a-variety-of--70126.jpg\">");
            out.println(userDetailsDecorater +"<p>You are logged in as " + currentUSER + "</p>");
            out.println("<p>Your current balance : $"+userCHECK.get(0).getBalance()+"</p></div><br style=\"clear:both\"><br>"+bigDivDecorater); 
            
            for(int x=0;x<products.size();x++){
            out.println("<div><pre><form action=\"PurchaseServlet\" method=\"post\"><input type=\"submit\" value=\"buy "+products.get(x).getpro_name()+"\" name=\"buyButton\"></form>&#9;"+products.get(x).getpro_name()+"&#9;<b>$</b>"+products.get(x).getprice()+"&#9;<b>in stock: </b>"+products.get(x).getavailable()+"&#9;"+"</pre><hr></div>");
            }
            out.println("</body>");
            out.println("</html>");
         }catch(Exception e) {System.out.println("greska.."+e);}
        }
        else {
            PrintWriter out = response.getWriter(); 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>BuyingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>ne valja password</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        
        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

   
    
    
}
