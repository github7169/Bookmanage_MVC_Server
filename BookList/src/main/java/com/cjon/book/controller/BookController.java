package com.cjon.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjon.book.dto.BookDTO;
import com.cjon.book.service.BookSearchKeywordList;
import com.cjon.book.service.BookService;

@Controller
public class BookController {

	@RequestMapping("/bookList")		
	public void getBookList(HttpServletRequest request, HttpServletResponse response) {
		
		String keyword = request.getParameter("keyword");
		String callback = request.getParameter("callback");
			
		BookDTO dto = new BookDTO();
		dto.setBtitle(keyword);
		
		BookService service = new BookSearchKeywordList();
		String result = service.execute(dto);
		
		response.setContentType("text/plain; charset=utf8");
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(callback + "(" + result + ")");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
