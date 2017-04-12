package ServPaket;

import classPaket.DBengine;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
        String newProductName = request.getParameter("NEWproductNAME");
        int newProductPrice = Integer.valueOf(request.getParameter("NEWproductPRICE"));
        int newProductStock = Integer.valueOf(request.getParameter("NEWproductSTOCK"));
        classPaket.DBengine.insertNewProduct(newProductName, newProductPrice, newProductStock);
        
        } catch (Exception e) {System.out.println("skip"+e);}
        
        classPaket.DBengine.ListAllProduct();
        List<classPaket.Proizvod> ProductList = DBengine.ProductList;
        
            try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>*webSTORE*</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div style='background-color:#99ccff'><h2 align='center'>List of all products:</h2></div>");
            out.println("<a href='newProduct.html'>New product</a>");
            out.println("<a href='editProduct.html'>Edit product</a>");
            out.println("<a href='delProduct.html'>Delete product</a>");
            out.println("<a href='servlet01'>USERS LIST</a>");
            out.println("<a href='buying.html'>****BUY PRODUCT***</a>");
            out.println("<div style='background-color:#99ccff'><p>ID - NAME - PRICE - LEFT:</p></div>");
            
            for(int i=0;i<ProductList.size();i++){
            out.println(ProductList.get(i) + "<hr>");
            }
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
