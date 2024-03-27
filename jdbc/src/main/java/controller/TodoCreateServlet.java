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

@WebServlet("/create")
public class TodoCreateServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");

    // 사용자가 입력한 todo 가져오기
    String title = req.getParameter("title");
    String description = req.getParameter("description");

    // DB 작업
    TodoDao dao = new TodoDao();

    TodoDto inserDto = new TodoDto();
    inserDto.setTitle(title);
    inserDto.setDescription(description);

    int result = dao.insert(inserDto);

    // 화면 이동(list)
    resp.sendRedirect("list");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    doGet(req, resp);
  }
}
