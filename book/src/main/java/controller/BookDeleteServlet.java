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

@WebServlet("/delete")
public class BookDeleteServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    // modify.jsp 넘긴 값 가져오기

    int code = Integer.parseInt(req.getParameter("code"));

    BookDao dao = new BookDao();
    int result = dao.delete(code);

    // 수정 성공시 list
    // 실패시 modify.jsp
    if (result > 0) {
      resp.sendRedirect("/list");
    } else {
      resp.sendRedirect("/view/delete.jsp");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    doGet(req, resp);
  }
}
