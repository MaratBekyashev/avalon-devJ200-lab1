/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JAVA
 */
public class HelloServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Double getRandomNumber(Double basis){
        /*
        * если поле содержит целое число, то пользователю возвращается страница, которая показывает любое целое число, 
        * превышающее исходное;
        */
        Double res;
        res = basis + Math.round(basis);
        return res;
    }
    
    private String parseString(String str){
    /* если строка содержит текст, то сервер возвращает страницу, которая показывает пользователю исходную строку
       и число слов в этой строке (словом считается любая непустая последовательность символов, которая завершается
       любым пробельным символом, синтаксическим символом или символом конца строки);       
    */       
       Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
       String[] tokens = pattern.split(str);
       String result = "\""+str + "\""+ "statistics: " + tokens.length + " tokens";
       return result;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String msg = request.getParameter("msg");
        if (msg.trim().isEmpty()){
            response.sendRedirect("index.html");
            return;        
        }
        Double num = null;
        try {
           num = Double.valueOf(msg);
        } catch (Exception ex){
           num =null;
        }
        PrintWriter out = response.getWriter();       
        if (num != null && msg.equals(msg)){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet response</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> "+ getRandomNumber(num)+"</h1>");
            out.println("</body>");
            out.println("</html>");
        } 
        else{
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet response</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> "+ parseString(msg)+"</h1>");
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
        
        System.out.println("doGet епта");  
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
