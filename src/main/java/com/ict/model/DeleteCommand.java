package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String cPage = request.getParameter("cPage");
		request.setAttribute("cPage", cPage);
		System.out.println("cPage-1: " + cPage);
		return "view/delete.jsp";
	}
}
