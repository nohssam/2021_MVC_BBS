package com.ict.db;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class DAO {
	private static SqlSession ss;
	private synchronized static SqlSession getSession() {
		if(ss == null) {
			ss = DBService.getFactory().openSession(false);
		}
		return ss;
	}
	
	public static List<BVO> getList(){
		List<BVO> list=null;
		list = getSession().selectList("list");
		return list;
	}
	
	public static int getInsert(BVO bvo) {
		int result = 0 ;
		result = getSession().insert("insert", bvo);
		ss.commit();
		return result;
	}
	
	public static BVO getOneList(String b_idx) {
		BVO bvo = null;
		bvo = getSession().selectOne("onelist", b_idx);
		return bvo;
	}
	
	public static int getDelete(BVO bvo) {
		int result=0;
		result = getSession().delete("delete", bvo);
		ss.commit();
		return result;
	}
	
	public static int getUpdate(BVO bvo) {
		int result =0 ;
		result = getSession().update("update", bvo);
		ss.commit();
		return result;
	}
}

















