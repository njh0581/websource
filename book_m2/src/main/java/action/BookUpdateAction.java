package action;

import dto.BookDto;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookUpdateAction implements Action {

  private String path;

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    // modify.jsp 넘긴 값 가져오기
    String code = req.getParameter("code");
    String price = req.getParameter("price");
    // 서비스 update
    BookService service = new BookServiceImpl();
    BookDto modifyDto = new BookDto();
    modifyDto.setCode(Integer.parseInt(code));
    modifyDto.setPrice(Integer.parseInt(price));

    boolean result = service.update(modifyDto);

    if (result) {
      path += "?code=" + modifyDto.getCode();
    } else {
      path = "/view/modify.jsp";
    }
    // 수정 내용 보여주기 read
    return new ActionForward(path, true);
  }
}
