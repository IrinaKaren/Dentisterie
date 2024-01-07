package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dents;
import model.Operation;
import model.Prioriter;

public class DentController extends HttpServlet {
    //ilay function rehefa valider ka tsy nanova n'inoninona dia le situation farany anaty base no atao an'le etablissement
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String[] dents = request.getParameterValues("dents");
            String priorite = request.getParameter("priorite");
            int idclient = Integer.parseInt(request.getParameter("idclient"));
            double argent = Double.parseDouble(request.getParameter("argent"));
        try {
            Timestamp date_now = Timestamp.valueOf(LocalDateTime.now());
            String[] new_situation = Dents.getNewSituation(idclient, dents);
            if(new_situation!=null){
                Dents.newSituation(idclient,new_situation);
            } 
            Prioriter.etablissementPrioriter(priorite, argent, idclient, date_now);
            response.getWriter().println(argent);
            request.setAttribute("listdents",Operation.getLastOperation(idclient,date_now));
            request.setAttribute("argent",argent);
            request.setAttribute("reste",(argent-Operation.getCoutTotal(idclient,date_now)));
            request.setAttribute("cout_total",Operation.getCoutTotal(idclient,date_now));
            RequestDispatcher dispatcher = request.getRequestDispatcher("list_dents_traiter.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace(response.getWriter());
        }  
//            for (String dent : new_situation) {
//                response.getWriter().println(dent);
//            }     
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
