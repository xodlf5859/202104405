<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<% response.setStatus(302); %>

<script>
alert("${message}");

if(window.opener.parent){
	window.opener.parent.location.reload();
}else{
	window.location.reload();
}
window.close();
</script>