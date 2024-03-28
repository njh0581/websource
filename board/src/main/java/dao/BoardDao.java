package dao;

import dto.BoardDto;
import dto.SearchDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDao {

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
    return con;
  }

  // 전체 게시물 개수 가져오기
  public int getRows(String criteria, String keyword) {
    int total = 0;
    try {
      con = getConnection();
      String sql = "";
      if (criteria.isEmpty()) {
        sql += "SELECT count(*) FROM board";
      } else {
        sql += "SELECT count(*) FROM board where " + criteria + " like ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, "%" + keyword + "%");
      }
      pstmt = con.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        total = rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }
    return total;
  }

  //전체 리스트 가져오기
  public List<BoardDto> getList(SearchDto searchDto) {
    List<BoardDto> list = new ArrayList<>();
    con = getConnection();
    int start = searchDto.getPage() * searchDto.getAmount();
    int end = (searchDto.getPage() - 1) * searchDto.getAmount();

    try {
      String sql = "SELECT bno,title,name,REGDATE,READ_COUNT,RE_LEV ";
      sql += "from (SELECT rownum AS rnum, A.* ";
      sql += "from (SELECT bno,title,name,REGDATE,READ_COUNT,RE_LEV ";
      sql += "FROM BOARD b WHERE bno > 0 ";
      if (searchDto.getCriteria().isEmpty()) {
        sql += "ORDER BY re_ref DESC, re_seq ASC) A ";
        sql += "WHERE rownum <= ?) WHERE rnum > ?";

        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, start);
        pstmt.setInt(2, end);
      } else {
        sql += "AND " + searchDto.getCriteria() + " like ? ";
        sql += "ORDER BY re_ref DESC, re_seq ASC) A ";
        sql += "WHERE rownum <= ?) WHERE rnum > ?";

        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, "%" + searchDto.getKeyword() + "%");
        pstmt.setInt(2, start);
        pstmt.setInt(3, end);
      }

      rs = pstmt.executeQuery();
      while (rs.next()) {
        BoardDto dto = new BoardDto();
        dto.setBno(rs.getInt(1));
        dto.setTitle(rs.getString(2));
        dto.setName(rs.getString(3));
        dto.setRegDate(rs.getDate(4));
        dto.setReadCount(rs.getInt(5));
        dto.setReLev(rs.getInt(6));

        list.add(dto);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }
    return list;
  }

  // 새글 작성
  public int create(BoardDto insertDto) {
    int result = 0;
    con = getConnection();
    String sql =
      "INSERT INTO board(bno,name,password,title,content,attach,RE_REF,RE_LEV,RE_SEQ) VALUES(board_seq.nextval,?,?,?,?,?,board_seq.currval,?,?)";
    try {
      pstmt = con.prepareStatement(sql);

      pstmt.setString(1, insertDto.getName());
      pstmt.setString(2, insertDto.getPassword());
      pstmt.setString(3, insertDto.getTitle());
      pstmt.setString(4, insertDto.getContent());
      pstmt.setString(5, insertDto.getAttach());
      pstmt.setInt(6, 0);
      pstmt.setInt(7, 0);
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }

    return result;
  }

  // 특정 글 조회
  public BoardDto getRow(int bno) {
    BoardDto dto = null;

    try {
      con = getConnection();
      String sql =
        "select bno,name,title,content,attach,re_ref,re_seq,re_lev from board where bno = ?";
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, bno);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        dto = new BoardDto();
        dto.setBno(rs.getInt("bno"));
        dto.setName(rs.getString("name"));
        dto.setTitle(rs.getString("title"));
        dto.setContent(rs.getString("content"));
        dto.setAttach(rs.getString("attach"));
        // reply에서 필요함
        dto.setReRef(rs.getInt("re_ref"));
        dto.setReSeq(rs.getInt("re_seq"));
        dto.setReLev(rs.getInt("re_lev"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }

    return dto;
  }

  //특정글 수정
  public int update(BoardDto updateDto) {
    //bno와 password 일치시 제목, 내용 수정
    int result = 0;
    con = getConnection();
    String sql =
      "update board set title = ?, content = ? where bno = ? and password = ?";
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, updateDto.getTitle());
      pstmt.setString(2, updateDto.getContent());
      pstmt.setInt(3, updateDto.getBno());
      pstmt.setString(4, updateDto.getPassword());
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }
    return result;
  }

  //특정글 삭제
  public int delete(BoardDto deleteDto) {
    //bno와 password가 일치하면 삭제
    int result = 0;
    con = getConnection();
    String sql = "Delete from board where bno = ? and password = ? ";
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, deleteDto.getBno());
      pstmt.setString(2, deleteDto.getPassword());
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }
    return result;
  }

  //댓글 작성
  public int reply(BoardDto replyDto) {
    int result = 0;
    con = getConnection();
    try {
      //원본글의 re_ref, re_seq, re_lev 가져오기
      int reRef = replyDto.getReRef();
      int reSeq = replyDto.getReSeq();
      int reLev = replyDto.getReLev();

      String sql =
        "UPDATE board SET re_seq = re_seq + 1 WHERE re_ref = ? AND re_seq > ?";
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, reRef);
      pstmt.setInt(2, reSeq);
      pstmt.executeUpdate();

      sql =
        "INSERT INTO board(bno,name,password,title,content,RE_REF,RE_LEV,RE_SEQ) VALUES(board_seq.nextval,?,?,?,?,?,?,?)";
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, replyDto.getName());
      pstmt.setString(2, replyDto.getPassword());
      pstmt.setString(3, replyDto.getTitle());
      pstmt.setString(4, replyDto.getContent());
      pstmt.setInt(5, reRef);
      pstmt.setInt(6, reLev + 1);
      pstmt.setInt(7, reSeq + 1);
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }

    return result;
  }

  // 조회수 업데이트
  public int updateCount(int bno) {
    int result = 0;
    con = getConnection();
    String sql = "UPDATE board SET READ_COUNT= READ_COUNT + 1 WHERE bno = ?";
    try {
      pstmt = con.prepareStatement(sql);
      // ? 해결
      pstmt.setInt(1, bno);
      result = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(con, pstmt);
    }
    return result;
  }

  // 검색
  public List<BoardDto> getSearchList(SearchDto searchDto) {
    List<BoardDto> list = new ArrayList<>();
    con = getConnection();
    // String sql = "SELECT bno,title,name,REGDATE,READ_COUNT,RE_LEV ";
    // sql += "FROM BOARD WHERE" + searchDto.getCriteria() + " like ?";
    // sql += "ORDER BY re_ref DESC, re_seq ASC";
    String sql =
      "SELECT bno,title,name,REGDATE,READ_COUNT,RE_LEV FROM BOARD WHERE " +
      searchDto.getCriteria() +
      " like ? ORDER BY re_ref DESC, re_seq ASC";
    try {
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, "%" + searchDto.getKeyword() + "%");
      rs = pstmt.executeQuery();
      while (rs.next()) {
        BoardDto dto = new BoardDto();
        dto.setBno(rs.getInt(1));
        dto.setTitle(rs.getString(2));
        dto.setName(rs.getString(3));
        dto.setRegDate(rs.getDate(4));
        dto.setReadCount(rs.getInt(5));
        dto.setReLev(rs.getInt(6));
        list.add(dto);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      close(con, pstmt, rs);
    }
    return list;
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
