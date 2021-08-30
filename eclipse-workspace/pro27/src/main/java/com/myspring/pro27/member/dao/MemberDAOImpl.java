package com.myspring.pro27.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro27.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	private SqlSession sqlSession; //세션에 세션템플릿 객체 생성됨
	
	@Override
	public List selectAllMemberList() throws DataAccessException{
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}
	
	@Override //매핑파일 인서트구문 id값과 네임스페이스 호출 id값을 보고 찾아들어가서 sql문이 실행됨
	//insert할 정보를 담고있는 memberVo객체를 전달, insert문 실행되고 memberVO  파라미터타입에 매핑이됨   
	public int insertMember(MemberVO memberVO)throws DataAccessException{
		int result=sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	} //xml 입력쿼리 실행
	
	@Override
	public int deleteMember(String id) throws DataAccessException{
		int result=sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}
	
	//로그인 기능 구현 추가
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException{
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		//selectone메서드호출 조회활 memberVo값을 두번째 인자로 줌 
		return vo;
	}
}