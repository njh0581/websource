package action;

import dto.MemberDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookLoginAction implements Action {

  private String path;

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    // userid, password 가져오기
    MemberDto dto = new MemberDto();
    dto.setUserid(req.getParameter("userid"));
    dto.setPassword(req.getParameter("password"));

    // service 호출
    BookService service = new BookServiceImpl();
    MemberDto loginDto = service.login(dto);

    // true로 결과를 받았다면 받은 정보 session 저장

    if (loginDto != null) {
      HttpSession session = req.getSession();
      session.setAttribute("loginDto", loginDto);
    } else {
      path = "/view/login.jsp";
    }
    // sendRedirect, book 목록 페이지
    return new ActionForward(path, true);
  }
}
