package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dents;
import model.Operation;

public class FormSoinsController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idclient = request.getParameter("idclient");
        String[] numeroDents = request.getParameterValues("numero_dent");
        String[] types = request.getParameterValues("type");
        double[] listCout = new double[numeroDents.length];
        double cout_soins = 0;

        for (int i = 0; i < numeroDents.length; i++) {
            try {
                String numeroDent = numeroDents[i];
                String type = types[i];
                listCout[i] = Dents.getCout(types[i], Integer.parseInt(numeroDent)); 
                cout_soins += Dents.getCout(types[i], Integer.parseInt(numeroDent));

                int idClientValue = Integer.parseInt(idclient);
                int numeroDentValue = Integer.parseInt(numeroDent); 
                Operation o = new Operation(idClientValue, numeroDentValue);
                o.operations(null, type, idClientValue, numeroDentValue);

                response.getWriter().println("Numero Dent: " + numeroDent + ", Type: " + type);
            } catch (Exception ex) {
                ex.printStackTrace(response.getWriter());
            }
        }

        response.getWriter().println("idclient : " + idclient);
        request.setAttribute("numeroDents",numeroDents);
        request.setAttribute("types",types);
        request.setAttribute("listCout",listCout);
        request.setAttribute("cout_final",cout_soins);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cout_soins.jsp");
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
