<%@page import="clases.FileManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	FileManager fm = new FileManager();
	if(request.getParameter("override") == null){
		fm.create(request.getParameter("file"),
				String.format("%s\n%s",
						fm.read(request.getParameter("file")),
						request.getParameter("content")
						));
	}else{
		fm.create(request.getParameter("file").toString().trim(),request.getParameter("content").toString().trim());
	}
%>