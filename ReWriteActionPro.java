package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardBean;
import model.BoardDAO;

/**
 * Servlet implementation class ReWriteActionPro
 */
@WebServlet("/ReWriteActionPro")
public class ReWriteActionPro extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		BoardBean bean = new BoardBean();
		bean.setWriter(request.getParameter("writer"));
		bean.setSubject(request.getParameter("subject"));
		bean.setEmail(request.getParameter("email"));
		bean.setPassword(request.getParameter("password"));
		bean.setContent(request.getParameter("content"));
		
		//the hidden values
		bean.setRef(Integer.parseInt(request.getParameter("ref")));
		bean.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		bean.setRe_level(Integer.parseInt(request.getParameter("re_level")));
	
		BoardDAO bdao = BoardDAO.getInstance();
		bdao.reWriteBoard(bean);
		
		RequestDispatcher dis = request.getRequestDispatcher("BoardListAction");
		dis.forward(request, response);
		
	}

}
