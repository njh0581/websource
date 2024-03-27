<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import = "dao.TodoDao"%>
<%@ page import = "dto.TodoDto"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%
  // 한글 처리
  request.setCharacterEncoding("utf-8");

  // 제목 클릭시 no 가져오기
  String no = request.getParameter("no");



  // DB 작업
  TodoDao dao = new TodoDao();
  TodoDto todo = dao.getRow(no);

  // todo를 read.jsp에 보여주기
  request.setAttribute("todo",todo);


  // 화면 이동
  pageContext.forward("modify.jsp");



%>