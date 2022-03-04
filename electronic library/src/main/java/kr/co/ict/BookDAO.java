package kr.co.ict;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BookDAO {

private DataSource ds = null;
	
	private static BookDAO dao = new BookDAO();
	
	
	// 생성자
	private BookDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 다른 파일에서 사용하게 해주는 메서드
	public static BookDAO getInstance() {
		if(dao == null) {
			dao = new BookDAO();
		}
		return dao;
	}
	
	// DB에 책 정보 적재
	public void insertBookData(int bNum, String bName, String bWriter,
			 		String bPub, String bCategory, boolean checkOut) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			
			String sql = "INSERT INTO book VALUES(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bNum);
			pstmt.setString(2, bName);
			pstmt.setString(3, bWriter);
			pstmt.setString(4, bPub);
			pstmt.setString(5, bCategory);
			pstmt.setBoolean(6, checkOut);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
				con.close();
				pstmt.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// DB에 책 정보 삭제
		public void deleteBookData(int bNum) {
			
			// 수업때는 유저 정보를 삭제하는 로직으로 세션아이디를 받아왔는데..
			// 책 정보도 세션을 줘야할지 그냥 갈지..
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				
				con = ds.getConnection();
				
				String sql = "DELETE FROM book WHERE bnum = ?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, bNum);
				
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					
					con.close();
					pstmt.close();
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	// DB 내 모든 책 정보 조회 
		public List<BookVO> getAllBookList(){
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			// try블럭 진입 전에 ArrayList 선언
			List<BookVO> BookList = new ArrayList<>();
			try {
				con = ds.getConnection();
				
				String sql = "SELECT * FROM bookinfo";
				pstmt = con.prepareStatement(sql);
			
				rs = pstmt.executeQuery();
			
				
				while(rs.next()) {
					int bNum = rs.getInt("bnum");
					String bName = rs.getString("bname");
					String bWriter = rs.getString("bwriter");
					String bPub = rs.getString("bpub");
					String bCategory = rs.getString("bcategory");
					boolean checkOut = rs.getBoolean("check_out");
					
					BookVO BookData = new BookVO(bNum, bName, bWriter, bPub, bCategory, checkOut);
					BookList.add(BookData);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					con.close();
					pstmt.close();
					rs.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return BookList;
		}
}