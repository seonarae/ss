package com.myspring.pro27.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.pro27.member.dao.MemberDAO;
import com.myspring.pro27.member.vo.MemberVO;

@Service("memberService") //컨트롤러 dao연결 ,서비스 클래스
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService{
	@Autowired //데이터 베이스 접근 위해 dao 인스턴스 주입받음
	private MemberDAO memberDAO;
	
	@Override
	public List listMembers() throws DataAccessException{
		List membersList = null;
		membersList = memberDAO.selectAllMemberList();
		return membersList;
	}
	@Override
	public int addMember(MemberVO member) throws DataAccessException {
		return memberDAO.insertMember(member); //memberDAO insertmember메서드 실행 
		//서비스는 DAO호출한 결과를 리턴하는 일만 한다
	}
	@Override
	public int removeMember(String id) throws DataAccessException{
		return memberDAO.deleteMember(id);
	}
	
	//로그인 기능 구현 추가
	@Override
	public MemberVO login(MemberVO memberVO) throws Exception{
		return memberDAO.loginById(memberVO);
	}
}