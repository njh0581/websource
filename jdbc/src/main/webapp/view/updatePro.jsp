<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import = "dao.TodoDao"%>
<%@ page import = "dto.TodoDto"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%
  // 한글 처리
  request.setCharacterEncoding("utf-8");

  //
  String completed = request.getParameter("completed");
  String description = request.getParameter("description");
  String no = request.getParameter("no");



  // DB 작업
  TodoDao dao = new TodoDao();
  
  TodoDto dto =new TodoDto();
  //value 가 없는 경우 checkbox, radio 의 경우에는 on 값을 가지고 오게 됨
  dto.setCompleted(Boolean.parseBoolean(completed));
  dto.setDescription(description);
  dto.setNo(Integer.parseInt(no));

  int result = dao.update(dto);

  // 화면 이동(read)
  response.sendRedirect("list.jsp");



%>