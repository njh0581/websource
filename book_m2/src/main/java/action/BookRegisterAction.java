package action;

import dto.MemberDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookRegisterAction implements Action {

  private String path;

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    // register.jsp 가져오기
    MemberDto regDto = new MemberDto();
    regDto.setUserid(req.getParameter("userid"));
    regDto.setPassword(req.getParameter("password"));
    regDto.setName(req.getParameter("name"));
    regDto.setEmail(req.getParameter("email"));

    // 서비스 호출
    BookService service = new BookServiceImpl();
    boolean result = service.register(regDto);

    // 결과에 따라 성공 시 login.jsp, 실패시 register.jsp
    if (!result) {
      path = "/view/register.jsp";
    }
    return new ActionForward(path, true);
  }
}
