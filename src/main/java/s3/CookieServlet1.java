package s3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet1
 */
@WebServlet("/cookies1")
public class CookieServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CookieServlet1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			request.getSession(); // will force the creation of a default Cookie

			// Get an array containing all of the cookies
			Cookie cookies[] = request.getCookies();

			// Write the page header
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet Cookie Information</title>");
			out.println("</head>");
			
			
			out.println("<body>");

			if ((cookies == null) || (cookies.length == 0)) {
				out.println("<center><h1>No Cookies found</h1>");
			} else {

				out.println("<center><h1>Cookies found</h1>");

				// Display a table with all of the info
				out.println("<table border>");
				out.println("<tr><th>Name</th><th>Value</th>" + "<th>Comment</th><th>Max Age</th></tr>");

				for (int i = 0; i < cookies.length; i++) {
					Cookie c = cookies[i];
					out.println("<tr><td>" + c.getName() + "</td><td>" + c.getValue() + "</td><td>" + c.getComment()
							+ "</td><td>" + c.getMaxAge() + "</td></tr>");

				}

				out.println("</table></center>");
			}

			// Wrap up
			out.println("</body>");
			out.println("</html>");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
