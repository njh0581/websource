<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ include file="/include/header.jsp"%>
<h3 class="border-bottom mb-3">도서 삭제</h3>
<form action='<c:url value="/delete.do"/>' method="post">
<div class="mb-3">
  <label for="code" class="form-label">code</label>
  <input type="text" class="form-control" id="code"  name="code">
  <small class="text-info">코드 4자리를 입력해주세요</small>
</div>
<div>
<button type="submit" class="btn btn-success">삭제</button>
<a class="btn btn-success" href='<c:url value="/list.do"/>'>목록</a>
</div>
</form>
<%@ include file="/include/section.jsp"%>
<script src='<c:url value="/js/delete.js"/>'></script>
<%@ include file="/include/footer.jsp"%>