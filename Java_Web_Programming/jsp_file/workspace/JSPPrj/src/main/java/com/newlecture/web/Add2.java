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

@WebServlet("/add2")
public class Add2 extends HttpServlet {
    
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
		
		
		String[] num_ = req.getParameterValues("array");
		int result = 0;
		
		for(int i=0; i<num_.length; i++) {
			if(isStringInteger(num_[i], 10)) {
				int num = Integer.parseInt(num_[i]);
				result += num;
			} else {
				out.println("유효한 숫자를 입력하세요.(정수만 입력 가능)");
				return;
			}
		}
		out.println("계산결과" + result);
		
		
		
	
		
	}
}
