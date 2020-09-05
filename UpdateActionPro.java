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
 * Servlet implementation class UpdateActionPro
 */
@WebServlet("/UpdateActionPro")
public class UpdateActionPro extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO bdao = BoardDAO.getInstance();
		BoardBean bean = bdao.getOneBoard(num);
		bean.setSubject(request.getParameter("subject"));
		bean.setPassword(request.getParameter("password"));
		bean.setContent(request.getParameter("content"));
		
		bdao.updateBoard(bean);
		
		RequestDispatcher dis = request.getRequestDispatcher("BoardListAction");
		dis.forward(request, response);
		
	}

}
