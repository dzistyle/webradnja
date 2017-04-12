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

public class servlet01 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try {
        String newUser = request.getParameter("NEWuserNAME");
        String newPass = request.getParameter("NEWuserPASS");
        int newUbalance = Integer.valueOf(request.getParameter("NEWuserBALANCE"));
        
        DBengine.insertNewUser(newUser, newPass, newUbalance);
        } catch (Exception e) {System.out.println("skip"+e);}
        
        DBengine.ListAllUsers();
        List<Korisnik> DBusers = DBengine.Klist;
        
        
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>*webSTORE*</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div style='background-color:#99ccff'><h2 align='center'>List of all registered users:</h2></div>");
            out.println("<a href='index.html'>New user</a>");
            out.println("<a href='editUser.html'>Edit user</a>");
            out.println("<a href='delUser.html'>Delete user</a>");
            out.println("<a href='NewProductServlet'>ALL PRODUCTS</a>");
            out.println("<a href='buying.html'>****BUY PRODUCT***</a>");
            out.println("<div style='background-color:#99ccff'><p>ID - USERNAME - PASSWORD - BALANCE:</p></div>");
            
            for(int i=0;i<DBusers.size();i++){
            out.println(DBusers.get(i) + "<hr>");
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
