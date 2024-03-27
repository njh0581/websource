package dao;

import dto.BookDto;
import dto.ChangeDto;
import dto.MemberDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookDao {

  private Connection con;
  private PreparedStatement pstmt;
  private ResultSet rs;

  //JDBC 단계
  //1. 드라이버 로드
  static {
    try {
      Class.forName("oracle.jdbc.OracleDriver");
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  //2. 커넥션 얻기
  public Connection getConnection() {
    Context initContext;
    try {
      initContext = new InitialContext();
      // java:/comp/env : 등록된 이름 관리
      Context envContext = (Context) initContext.lookup("java:/comp/env");
      DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
      con = ds.getConnection();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //etc.
    // String url = "jdbc:oracle:thin:@localhost:1521:xe";
    // String user = "c##test2";
    // String password = "test";
    // try {
    //   con = DriverManager.getConnection(url, user, password);
    // } catch (SQLException e) {
    //   // TODO Auto-generated catch block
    //   e.printStackTrace();
    // }
    return con;
  }

  //3
  public List<BookDto> getList() {
    List<BookDto> list = new ArrayList<>();

    con = getConnection();
    String sql =
      "select code, title, writer, price from booktbl order by code asc";
    try {
      pstmt = con.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        BookDto dto = new BookDto();
        dto.setCode(rs.getInt("code"));
        dto.setTitle(rs.getString("title"));
        dto.setWriter(rs.getString("writer"));
        dto.setPrice(rs.getInt("price"));

        list.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }

    return list;
  }

  public BookDto getRow(int code) {
    BookDto dto = null;

    con = getConnection();
    String sql = "select * from booktbl where code=?";
    try {
      pstmt = con.prepareStatement(sql);
      // ? 해결
      pstmt.setInt(1, code);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        dto = new BookDto();
        dto.setCode(rs.getInt("code"));
        dto.setTitle(rs.getString("title"));
        dto.setWriter(rs.getString("writer"));
        dto.setPrice(rs.getInt("price"));
        dto.setDescription(rs.getString("description"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }
    return dto;
  }

  public int update(BookDto updateDto) {
    int result = 0;
    con = getConnection();
    String sql = "UPDATE BOOKTBL SET price = ? WHERE code = ?";
    try {
      pstmt = con.prepareStatement(sql);
      // ? 해결
      pstmt.setInt(1, updateDto.getPrice());
      pstmt.setInt(2, updateDto.getCode());
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt);
    }
    return result;
  }

  public int insert(BookDto insertDto) {
    int result = 0;

    con = getConnection();
    String sql =
      "INSERT INTO BOOKTBL (code, title, writer, price, description) values(?,?,?,?,?)";
    try {
      pstmt = con.prepareStatement(sql);
      // ? 해결
      pstmt.setInt(1, insertDto.getCode());
      pstmt.setString(2, insertDto.getTitle());
      pstmt.setString(3, insertDto.getWriter());
      pstmt.setInt(4, insertDto.getPrice());
      pstmt.setString(5, insertDto.getDescription());
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }

    return result;
  }

  public List<BookDto> getSearchList(String criteria, String keyword) {
    List<BookDto> list = new ArrayList<>();
    con = getConnection();
    String sql = "";
    if (criteria.equals("code")) {
      // 검색 기준이 code 라면
      sql = "SELECT * FROM BOOKTBL WHERE code=?";
    } else {
      // 검색 기준이 writer 라면
      sql = "SELECT * FROM BOOKTBL WHERE writer=?";
    }
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, keyword);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        BookDto dto = new BookDto();
        dto.setCode(rs.getInt("code"));
        dto.setTitle(rs.getString("title"));
        dto.setWriter(rs.getString("writer"));
        dto.setPrice(rs.getInt("price"));

        list.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }

    return list;
  }

  public int delete(int code) {
    int result = 0;

    con = getConnection();
    String sql = "DELETE FROM booktbl WHERE code = ?";
    try {
      pstmt = con.prepareStatement(sql);
      // ? 해결
      pstmt.setInt(1, code);
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }

    return result;
  }

  //member 작업
  public MemberDto isLogin(MemberDto loginDto) {
    //로그인
    // SELECT userid,name FROM MEMBERTBL WHERE userid = 'hong123' AND PASSWORD = 'hong123'
    MemberDto dto = null;
    con = getConnection();
    String sql =
      "SELECT userid,name FROM MEMBERTBL WHERE userid = ? AND PASSWORD = ?";
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, loginDto.getUserid());
      pstmt.setString(2, loginDto.getPassword());
      rs = pstmt.executeQuery();
      if (rs.next()) {
        dto = new MemberDto();
        dto.setUserid(rs.getString("userid"));
        dto.setName(rs.getString("name"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }
    return dto;
  }

  public int passwordChange(ChangeDto changeDto) {
    //비밀번호 변경
    //UPDATE MEMBERTBL SET PASSWORD 'hong456' WHERE userid = 'hong123'
    int result = 0;
    con = getConnection();
    String sql = "UPDATE MEMBERTBL SET PASSWORD = ? WHERE userid = ?";
    try {
      pstmt = con.prepareStatement(sql);
      // ? 해결
      pstmt.setString(1, changeDto.getNewPassword());
      pstmt.setString(2, changeDto.getUserid());
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt);
    }
    return result;
  }

  public int register(MemberDto insertDto) {
    //회원가입
    //INSERT INTO MEMBERTBL (USERID,PASSWORD,name,EMAIL) values('hing123','hong123','홍길동','hong123@gmail.com');
    int result = 0;
    con = getConnection();
    String sql =
      "INSERT INTO MEMBERTBL (USERID,PASSWORD,name,EMAIL) values(?,?,?,?)";
    try {
      pstmt = con.prepareStatement(sql);
      // ? 해결
      pstmt.setString(1, insertDto.getUserid());
      pstmt.setString(2, insertDto.getPassword());
      pstmt.setString(3, insertDto.getName());
      pstmt.setString(4, insertDto.getEmail());
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }

    return result;
  }

  // public int leave(String password) {
  //   int result = 0;

  //   con = getConnection();
  //   String sql = "DELETE FROM membertbl WHERE password = ?";
  //   try {
  //     pstmt = con.prepareStatement(sql);
  //     // ? 해결
  //     pstmt.setString(1, password);
  //     result = pstmt.executeUpdate();
  //   } catch (SQLException e) {
  //     e.printStackTrace();
  //   } finally {
  //     close(con, pstmt, rs);
  //   }

  //   return result;
  // }
  public int memberDel(MemberDto delDto) {
    int result = 0;

    con = getConnection();
    String sql = "DELETE FROM membertbl WHERE userid = ? and password = ?";
    try {
      pstmt = con.prepareStatement(sql);
      // ? 해결
      pstmt.setString(1, delDto.getUserid());
      pstmt.setString(2, delDto.getPassword());
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }

    return result;
  }

  //4. 자원 정리
  public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
    try {
      if (rs != null) rs.close();
      if (pstmt != null) pstmt.close();
      if (con != null) con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void close(Connection con, PreparedStatement pstmt) {
    try {
      if (pstmt != null) pstmt.close();
      if (con != null) con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
