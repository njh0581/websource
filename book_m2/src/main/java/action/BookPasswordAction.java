package action;

import dto.ChangeDto;
import dto.MemberDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookPasswordAction implements Action {

  public String path;

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    // pwdChange.jsp 에서 넘긴값 가져오기
    String password = req.getParameter("password");
    String newPassword = req.getParameter("new-password");
    String confirmPassword = req.getParameter("confirm-password");

    // 아이디와 현재 비밀번호를 가지고 있는지 확인(서비스 메소드 호출)
    HttpSession session = req.getSession();
    MemberDto loginDto = (MemberDto) session.getAttribute("loginDto");

    MemberDto input = new MemberDto();
    input.setUserid(loginDto.getUserid());
    input.setPassword(password);

    BookService service = new BookServiceImpl();
    if (service.login(input) == null) {
      path = "/view/pwdChange.jsp";
    } else {
      // 사용자가 존재한다면 비밀번호 변경 서비스 메소드 호출
      ChangeDto changeDto = new ChangeDto();
      changeDto.setUserid(loginDto.getUserid());
      changeDto.setNewPassword(newPassword);
      if (service.change(changeDto)) {
        // 변경 완료 여부에 따라 true 이면 세션 제거후 로그인 페이지로 이동
        session.invalidate();
      } else {
        // false면 pwdChange.jsp로 이동
        path = "/view/pwdChange.jsp";
      }
    }
    return new ActionForward(path, true);
  }
}
