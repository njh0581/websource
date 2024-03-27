<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/include/header.jsp"%>
<h3 class="border-bottom mb-3">도서수정</h3>
<form action='<c:url value="/update.do"/>' method="post">
<div class="row mb-3">
  <div class="col">
    <input type="text" class="form-control" placeholder="코드" name="code" id="code">
  </div>
  <div class="col">
    <input type="text" class="form-control" placeholder="가격" name="price" id="price">
  </div>
</div>
<div>
   <button class="btn btn-success" type="submit">수정</button>
   <a class="btn btn-primary" href='<c:url value="/list.do"/>'>목록</a>
</div>
</form>
<%@ include file="/include/section.jsp"%>
<script src='<c:url value="/js/modify.js"/>'></script>
<%@ include file="/include/footer.jsp"%>