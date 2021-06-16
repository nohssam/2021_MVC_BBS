package com.ict.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.BVO;
import com.ict.db.CVO;
import com.ict.db.DAO;

public class OneListCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String b_idx = request.getParameter("b_idx");
		String cPage = request.getParameter("cPage");
		System.out.println("cPage-1: " + cPage);
		request.setAttribute("cPage", cPage);
		// 조회수 업데이터 
		int result = DAO.getHitUp(b_idx);
		
		// 내용 가져오기
		BVO bvo = DAO.getOneList(b_idx);
		// 수정과 삭제를 위해서 session 저장
		request.getSession().setAttribute("bvo", bvo);
		
		// b_idx를 가지고 관련 댓글 가져오기 
		List<CVO> c_list = DAO.getcList(b_idx);
		request.setAttribute("c_list", c_list);
		
		return "view/onelist.jsp";
	}
}
