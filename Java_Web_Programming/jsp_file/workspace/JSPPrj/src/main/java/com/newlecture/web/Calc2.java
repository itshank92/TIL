package com.newlecture.web;

import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// ServletContext 객체 생성 >> Request객체의 getServletContext사용해서 연결
		ServletContext application = req.getServletContext();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		Cookie[] cookies = req.getCookies();
		
		int v = Integer.parseInt(req.getParameter("num")); 
		String op = req.getParameter("operator");
		
		if(op.equals("=")) {
			int result = 0;
			
//			int x = (Integer)application.getAttribute("value");
			int x = 0;
			for(Cookie c:cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
				}
			}
			
			
			int y = v;
//			String operator = (String)application.getAttribute("op");
			String operator = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("op")) {
					operator = c.getValue();
				}
			}
			
			
			if(operator.equals("+")) {
				result = x + y;
			} else {
				result = x - y;
			}
			out.printf("result is %d\n", result);
		} else {
//			application.setAttribute("value", v);
//			application.setAttribute("op", op);
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			
			resp.addCookie(valueCookie);
			resp.addCookie(opCookie);
			resp.sendRedirect("calc2.html");
		}
		
	}
}
