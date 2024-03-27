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

@WebServlet("/read")
public class BookReadServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");

    String code = req.getParameter("code");

    BookDao dao = new BookDao();
    BookDto dto = dao.getRow(Integer.parseInt(code));

    req.setAttribute("dto", dto);

    RequestDispatcher rd = req.getRequestDispatcher("/view/read.jsp");
    rd.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    doGet(req, resp);
  }
}
