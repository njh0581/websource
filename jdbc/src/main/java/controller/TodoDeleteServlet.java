package controller;

import dao.TodoDao;
import dto.TodoDto;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class TodoDeleteServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    // 한글 처리
    req.setCharacterEncoding("utf-8");

    // no 가져오기
    String no = req.getParameter("no");

    // DB 작업
    TodoDao dao = new TodoDao();
    int result = dao.delete(no);
    // 화면 이동(read)
    resp.sendRedirect("list");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    doGet(req, resp);
  }
}
