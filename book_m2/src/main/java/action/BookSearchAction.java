package action;

import dto.BookDto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookSearchAction implements Action {

  private String path;

  @Override
  public ActionForward execute(HttpServletRequest req) throws Exception {
    // search.jsp 넘긴값 가져오기
    String criteria = req.getParameter("criteria");
    String keyword = req.getParameter("keyword");

    // service 호출
    BookService service = new BookServiceImpl();
    List<BookDto> list = service.searchListAll(criteria, keyword);

    // 검색 결과를 담은 후 이동
    req.setAttribute("list", list);

    return new ActionForward(path, false);
  }
}
