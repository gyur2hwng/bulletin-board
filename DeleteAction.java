package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;

/**
 * Servlet implementation class DeleteAction
 */
@WebServlet("/DeleteAction")
public class DeleteAction extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDAO bdao = BoardDAO.getInstance();
		bdao.deleteBoard(num);
		
		RequestDispatcher dis = request.getRequestDispatcher("BoardListAction");
		dis.forward(request, response);
		
	}

}
