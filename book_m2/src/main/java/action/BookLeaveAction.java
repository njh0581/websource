package action;

import dto.MemberDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookLeaveAction implements Action {

  private String path;

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    // String password = req.getParameter("password");

    // HttpSession session = req.getSession();
    // session.invalidate();

    // BookService service = new BookServiceImpl();
    // boolean result = service.leave(password);

    MemberDto delDto = new MemberDto();
    delDto.setUserid(req.getParameter("userid"));
    delDto.setPassword(req.getParameter("password"));

    BookService service = new BookServiceImpl();
    if (service.leave(delDto)) {
      //탈퇴시 세션 제거
      HttpSession session = req.getSession();
      session.invalidate();
    } else {
      path = "/view/leave.jsp";
    }

    return new ActionForward(path, true);
  }
}
