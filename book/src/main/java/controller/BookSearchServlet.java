package controller;

import dao.BookDao;
import dto.BookDto;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class BookSearchServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    //검색 기준(criteria) 검색어(keyward) 가져오기
    String criteria = req.getParameter("criteria");
    String keyword = req.getParameter("keyword");
    // System.out.println(criteria);
    // System.out.println(keyword);

    BookDao dao = new BookDao();
    List<BookDto> list = dao.getSearchList(criteria, keyword);

    req.setAttribute("list", list);

    RequestDispatcher rd = req.getRequestDispatcher("/view/list.jsp");
    rd.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    doGet(req, resp);
  }
}
