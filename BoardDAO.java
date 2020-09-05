package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	
	public static BoardDAO dao = new BoardDAO();
	public static BoardDAO getInstance() {
		return dao;
	}
	
	//==============================================
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 1. Connection
	public Connection getConnection() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/ev");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// 2. Adds a post to db
	public void insertBoard(BoardBean board) {
		int ref = 0;
		int num = 0;
		
		try {
			conn = getConnection();
			
			String refSql = "select max(ref) from board";
			pstmt = conn.prepareStatement(refSql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				ref = rs.getInt(1) + 1;
			}
			
			String numSql = "select max(num) from board";
			pstmt = conn.prepareStatement(numSql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}
			
			String sql = "insert into board (num, writer, email, subject, "
					+ "password, reg_date, ref, re_step, re_level, "
					+ "readcount, content) values (?,?,?,?,?,now(),?,1,1,0,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getEmail());
			pstmt.setString(4, board.getSubject());
			pstmt.setString(5, board.getPassword());
			pstmt.setInt(6, ref);
			pstmt.setString(7, board.getContent());
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	// 3. Counts number of posts
	public int getAllCount() {
		int count = 0;
		try {
			conn = getConnection();
			
			String sql = "select count(*) from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
		return count;
	}
	
	// 4. Gets all posts
	public Vector<BoardBean> getAllBoard(int start, int count) {
		Vector<BoardBean> v = new Vector<>();
		BoardBean bean = null;
		
		try {
			conn = getConnection();
			
			String sql = "select * from board order by ref desc, re_level limit ?, ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bean = new BoardBean();
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				
				v.add(bean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return v;
	}
	
	// 5. Gets one post 
	public BoardBean getOneBoard(int num) {
		BoardBean bean = null;
		
		try {
			conn = getConnection();
			
			String sql = "select * from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bean = new BoardBean();
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return bean;
	}
	
	// 6. Gets one post + update readcount 
	public BoardBean getOneUpdateBoard(int num) {
		BoardBean bean = null;
		
		try {
			conn = getConnection();
			
			String rcSql = "update board set readcount=readcount+1 where num=?";
			pstmt = conn.prepareStatement(rcSql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			String sql = "select * from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeQuery();
			
			if (rs.next()) {
				bean = new BoardBean();
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return bean;
	}
	
	// 7. Gets Password of a post
	public String getPass(int num) {
		String password = "";
		
		try {
			conn = getConnection();
			
			String sql = "select password from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				password = rs.getString(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) {}}
			if(rs != null) { try { rs.close(); } catch (SQLException e) {}}
			if(conn != null) { try { conn.close(); } catch (SQLException e) {}}
		}
		
		return password;
	}
	
	// 8. Updates a post
	public void updateBoard(BoardBean bean) {
		try {
			conn = getConnection();
			
			String sql = "update board set subject=?, content=? where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getSubject());
			pstmt.setString(1, bean.getContent());
			pstmt.setInt(3, bean.getNum());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	// 9. Deletes a post
	public void deleteBoard(int num) {
		try {
			conn = getConnection();
			
			String sql = "delete from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) {}}
			if(conn != null) { try { conn.close(); } catch (SQLException e) {}}
		}
	}
	
	// 10. Posts a reply
	public void reWriteBoard(BoardBean bean) {
		int num = 0;
		
		try {
			conn = getConnection();
			
			String numSql = "select max(num) from board";
			pstmt = conn.prepareStatement(numSql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}
			
			// Update the re_level of all the replies of one post
			String levelSql = "update board set re_level=re_level+1 where "
					+ "ref=? and re_level>?";
			pstmt = conn.prepareStatement(levelSql);
			pstmt.setInt(1, bean.getRef());
			pstmt.setInt(2, bean.getRe_level());
			pstmt.executeUpdate();
			
			String sql = "insert into board (num, writer, email, subject, password, "
					+ "reg_date, ref, re_step, re_level, readcount, content) "
					+ "values(?,?,?,?,?,now(),?,?,?,0,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bean.getWriter());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getSubject());
			pstmt.setString(5, bean.getPassword());
			pstmt.setInt(6, bean.getRef());
			pstmt.setInt(7, bean.getRe_step() + 1);
			pstmt.setInt(8, bean.getRe_level() + 1);
			pstmt.setString(9, bean.getContent());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) {}}
			if(rs != null) { try { rs.close(); } catch (SQLException e) {}}
			if(conn != null) { try { conn.close(); } catch (SQLException e) {}}
		}
	}
	
	
}
