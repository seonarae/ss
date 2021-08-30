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
	private SqlSession sqlSession; //���ǿ� �������ø� ��ü ������
	
	@Override
	public List selectAllMemberList() throws DataAccessException{
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}
	
	@Override //�������� �μ�Ʈ���� id���� ���ӽ����̽� ȣ�� id���� ���� ã�Ƶ��� sql���� �����
	//insert�� ������ ����ִ� memberVo��ü�� ����, insert�� ����ǰ� memberVO  �Ķ����Ÿ�Կ� �����̵�   
	public int insertMember(MemberVO memberVO)throws DataAccessException{
		int result=sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	} //xml �Է����� ����
	
	@Override
	public int deleteMember(String id) throws DataAccessException{
		int result=sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}
	
	//�α��� ��� ���� �߰�
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException{
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		//selectone�޼���ȣ�� ��ȸȰ memberVo���� �ι�° ���ڷ� �� 
		return vo;
	}
}