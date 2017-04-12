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

public class servletDel extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String delName = request.getParameter("delNAME");
        String delPass = request.getParameter("delPASS");
        DBengine.ListSpecUser(delName, delPass);
        List<Korisnik> specUser = DBengine.specKlist;
        int delID = specUser.get(0).getId();
        
      
        if(delName.equals(specUser.get(0).getUsername())&&delPass.equals(specUser.get(0).getPassword())){
         PrintWriter out = response.getWriter();  
         
         try {
             DBengine.DeleteUser(delID);
             System.out.println("da li radi");
         } catch (Exception e) {System.out.println("ne radi del (servlet) "+e);}
         
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>edit user</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h4>User deleted.</h4>");
            out.println("<a href='servlet01'>Back to all users list.</a>");
            out.println("</body>");
            out.println("</html>");
      }else {
         PrintWriter out = response.getWriter();
         out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>edit error</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h4>wrong username or password</h4>");
            out.println("<a href='delUser.html'>BACK</a>");;
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
