package edu.pnu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.JDBConnect;
import edu.pnu.domain.MemberVO;

public class MemberDAO extends JDBConnect{
	
	public MemberDAO() {
		super();
	}
	public List<MemberVO> getAllMembers() {
		List<MemberVO> list = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			 stmt = con.createStatement();
			 rs = stmt.executeQuery("SELECT * FROM member ORDER BY id ASC");
			while (rs.next()) {
				list.add(MemberVO.builder()
						.id(rs.getInt("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public MemberVO getMember(Integer id) {
		
		return null;
	
	}
	public int addMember(MemberVO memberVO) {
		return 0;
	}
	public int updateMembers(MemberVO memberVO) {
		return 0;
	}
	public int removeMember(Integer id) {
		return 0;
	}
}