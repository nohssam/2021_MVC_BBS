package com.ict.model;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.BVO;
import com.ict.db.DAO;

public class Delete_OKCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String cPage = request.getParameter("cPage");
		
		// 댓글이 없으면 삭제 가능, 댓글이 이으면 오류 발생
		// 외래키로 연결 되어 있어서 참조무결성 조건에 의해서 오류 발생
		
		
		BVO bvo = (BVO)request.getSession().getAttribute("bvo");
		int result = DAO.getDelete(bvo);
		if(result>0) {
			String path = request.getServletContext().getRealPath("/upload");
			String file_name = bvo.getFile_name();
			try {
				File file = new File(path+"/"+new String(file_name.getBytes("utf-8")));
				if(file.exists())  file.delete(); 
			} catch (Exception e) {
			}
			return "MyController?cmd=list&cPage="+cPage;
		}
		return null;
	}
}
