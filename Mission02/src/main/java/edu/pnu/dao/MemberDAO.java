package edu.pnu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.JDBConnect;
import edu.pnu.domain.MemberVO;

public class MemberDAO extends JDBConnect {
	List<MemberVO> list;

	public MemberDAO() {
		super();
		list = new ArrayList<>();
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
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			// rs = st.executeQuery(String.format("SELECT * FROM member WHERE id = %d",
			// id));
			rs = stmt.executeQuery("SELECT * FROM member WHERE id = " + id);
			if (rs.next()) {
				return MemberVO.builder()
						.id(rs.getInt("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build();
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
		return null;
	}

	// 특정 회원 데이터가 존재하는지 확인
	private MemberVO getMember(MemberVO memberVO) {
		for (MemberVO m : list) {
			if (m.getId() == memberVO.getId())
				return m;
		}
		return null;
	}

	public int addMember(MemberVO memberVO) {
		if (getMember(memberVO) != null)
			return 0;
		
		PreparedStatement psmt = null;
		String query = "INSERT INTO member (id, pass, name) VALUES (?, ?, ?);";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, memberVO.getId());
			psmt.setString(2, memberVO.getPass());
			psmt.setString(3, memberVO.getName());
			return psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int updateMembers(MemberVO memberVO) {
	    Statement stmt = null;
	    String query = "UPDATE member SET";
	    String query1 = null;
	    if (memberVO.getName() == null && memberVO.getPass() == null)	return 0;


	    if (memberVO.getName() != null)	query1 += (" name='" + memberVO.getName() + "'");
	    if (query1 != null) query1 += ",";
	    if (memberVO.getPass() != null)	query1 += (" pass='" + memberVO.getPass() + "'");
	    
	    query = query + (query1 + " where id=" + memberVO.getId());
	    
	    try {
	        stmt = con.createStatement();
	        return stmt.executeUpdate(query);
	    } catch(Exception e) {
	    	e.printStackTrace();
	    } finally {
            try {
            	if (stmt != null)	stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	    return 0;
	}

	public int removeMember(Integer id) {
		PreparedStatement psmt = null;
		String query = "DELETE FROM member WHERE id = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}