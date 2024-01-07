package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dents;
import model.Operation;

public class FormSituationDentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idclient = request.getParameter("idclient");
        String argent = request.getParameter("argent");
        String[] numeroDents = request.getParameterValues("numero_dent");
        String[] degats = request.getParameterValues("degat");
        String[] types = request.getParameterValues("type");
        for (int i = 0; i < numeroDents.length; i++) {
            try {
                String numeroDent = numeroDents[i];
//                String degat = degats[i];
//                String type = types[i];
                
                int idClientValue = Integer.parseInt(idclient);
                int numeroDentValue = Integer.parseInt(numeroDent); 
//                Dents dent = new Dents(numeroDentValue,idClientValue,degat,type);
//                Dents dent = new Dents(numeroDentValue,idClientValue,null,null);
//                dent.situation();

                response.getWriter().println("Numero Dent: " + numeroDent);
            } catch (Exception ex) {
                ex.printStackTrace(response.getWriter());
            }
        }
        request.setAttribute("idclient",idclient);
        request.setAttribute("argent",argent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("option_traitement.jsp");
        dispatcher.forward(request, response);
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
