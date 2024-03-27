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

@WebServlet("/update")
public class TodoUpdateServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    // 한글 처리
    req.setCharacterEncoding("utf-8");

    //
    String completed = req.getParameter("completed");
    String description = req.getParameter("description");
    String no = req.getParameter("no");

    // DB 작업
    TodoDao dao = new TodoDao();
    TodoDto dto = new TodoDto();
    //value 가 없는 경우 checkbox, radio 의 경우에는 on 값을 가지고 오게 됨
    dto.setCompleted(Boolean.parseBoolean(completed));
    dto.setDescription(description);
    dto.setNo(Integer.parseInt(no));

    int result = dao.update(dto);

    // servlet list 경로로 이동
    resp.sendRedirect("list");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    doGet(req, resp);
  }
}
