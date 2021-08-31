package com.newlecture.web;

import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class Add extends HttpServlet {
    
	public static boolean isStringInteger(String stringToCheck, int radix) {
        Scanner sc = new Scanner(stringToCheck.trim());
        if(!sc.hasNextInt(radix)) return false;
        sc.nextInt(radix);
        return !sc.hasNext();
    }
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		
		String num1 = req.getParameter("num1");
		String num2 = req.getParameter("num2");
		
		if(isStringInteger(num1, 10) && isStringInteger(num2, 10)) {
			int n1 = Integer.parseInt(num1);
			int n2 = Integer.parseInt(num2);
			out.println("계산결과" + (n1+n2));
		} else {
			out.println("유효한 숫자를 입력하세요.(정수만 입력 가능)");
		}
		
		
	
		
	}
}
