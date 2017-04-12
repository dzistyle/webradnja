package ServPaket;

import classPaket.DBengine;
import classPaket.Korisnik;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PurchaseServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        DBengine.ListSpecProduct(request.getParameter("buyButton").substring(4));
        int currentUserID = BuyingServlet.userID;
        int currentUserBalance = BuyingServlet.userBL;
       
                List<Korisnik> specUsers = DBengine.specKlist;
                String currUser = specUsers.get(0).getUsername();
                String currPass = specUsers.get(0).getPassword();
        
        List<classPaket.Proizvod> selectedProduct = DBengine.specProductList;
        
        int productID = selectedProduct.get(0).getId();
        int productPRICE = selectedProduct.get(0).getprice();
        int Stockediter = 1;
        int newInStock = selectedProduct.get(0).getavailable()-Stockediter;
        int newBalance = currentUserBalance - productPRICE;
        
        if(newBalance>=0&&newInStock>=0) {
        
        classPaket.DBengine.updPRODUCTandUSER(productID, currentUserID, newInStock, newBalance);
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>PurchaseServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Process "+request.getParameter("buyButton")+" is successfull.</p>");
            out.println("<form action='BuyingServlet' method='post'><input hidden='' value='"+currUser+"' name='userNAME'><input hidden='' value='"+currPass+"' name='passWORD'><input type='submit' value='back'></form>");
            out.println("</body>");
            out.println("</html>");
        }
        } else {
            try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>PurchaseServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Process "+request.getParameter("buyButton")+" failed. Check your balance and product stock.</p>");
            out.println("<form action='BuyingServlet' method='post'><input hidden='' value='"+currUser+"' name='userNAME'><input hidden='' value='"+currPass+"' name='passWORD'><input type='submit' value='back'></form>");
            out.println("</body>");
            out.println("</html>");
        }
            
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
