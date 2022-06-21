package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{

	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Member loginMember(Member m) {
		
		Member loginUser = memberDao.loginMember(sqlSession,m);
		
		/*
		 * 
		 * SqlSessionTemplate 객체를 bean 등록 후 @Autowired 해주면
		 * 스프링 컨테이너가 사용 후 자동으로 반납해주기 때문에 close()를 할 필요가 없다
		 * */
	return loginUser;
	}

	@Override
	public int insertMember(Member m) {
		
		
		int result = memberDao.insertMember(sqlSession,m);
		
		return result;
				
	}

	@Override
	public int updateMember(Member m) {
		
		int result = memberDao.updateMember(sqlSession,m);
		
		return result;
				
	}

	@Override
	public int deleteMember(String  userId) {
		
		int result = memberDao.deleteMember(sqlSession,userId);
		
		return result;
	}

	@Override
	public int idCheck(String userId) {
		return 0;
	}



}
