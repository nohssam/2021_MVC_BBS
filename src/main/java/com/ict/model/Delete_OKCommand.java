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
		// 방법1)  try ~ catch : error.jsp로 이동 
		// 방법2)  원글과 관련된 댓글 모두 삭제 
		// 방버3)  원글을 삭제된 글 임을 표시(원글커럼을 늘려서 )
		
		BVO bvo = (BVO)request.getSession().getAttribute("bvo");
		
		// 방법2)
		// 관련되 댓글 모두 삭제
		int res = DAO.getComm_AllDelete(bvo.getB_idx());
		
		// 원글 삭제
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
