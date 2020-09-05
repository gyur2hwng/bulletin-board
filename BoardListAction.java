package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardBean;
import java.util.Vector;

/**
 * Servlet implementation class BoardListAction
 */
@WebServlet("/BoardListAction")
public class BoardListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		// number of posts per page 
		int pageSize = 10;
		String pageNumber = request.getParameter("pageNum"); //pageNum is the variable for page count ex) [1]
		if (pageNumber == null) { //if you click "View All Posts" in the beginning
			pageNumber = "1";
		}
		
		BoardDAO bdao = BoardDAO.getInstance();
		// number of total posts
		int count = bdao.getAllCount();
		
		// current Page
		int currentPage = Integer.parseInt(pageNumber);
		// sets the beginning post number 
		int startRow = (currentPage - 1) * pageSize; //ex) if currentPage is [3], shows from post #21 
		
		// Save posts
		Vector<BoardBean> v = new Vector<>();
		v = bdao.getAllBoard(startRow, pageSize); // calls 10 posts at a time
		int number = count - (currentPage - 1) * pageSize;
		
		request.setAttribute("v", v);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", count);
		request.setAttribute("currentPage", currentPage);
		
		RequestDispatcher dis = request.getRequestDispatcher("02_boardList.jsp");
		dis.forward(request, response);
	}

}
