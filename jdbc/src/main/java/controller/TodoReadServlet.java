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

@WebServlet("/read")
public class TodoReadServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    // 한글 처리
    req.setCharacterEncoding("utf-8");

    // 제목 클릭시 no 가져오기
    String no = req.getParameter("no");

    // DB 연동
    TodoDao dao = new TodoDao();
    TodoDto todo = dao.getRow(no);

    // todo를 read.jsp에 보여주기
    req.setAttribute("todo", todo);

    RequestDispatcher rd = req.getRequestDispatcher("/view/read.jsp");
    rd.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    doGet(req, resp);
  }
}
