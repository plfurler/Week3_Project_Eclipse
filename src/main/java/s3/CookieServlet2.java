package s3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet2
 */
@WebServlet("/cookies2")
public class CookieServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final Map<String, String> books = new HashMap<String, String>();

	// initialize Map books
	public void init() {
		books.put("C", "0130895725");
		books.put("C++", "0130895717");
		books.put("Java", "0130125075");
		books.put("VB6", "0134569555");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CookieServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie cookies[] = request.getCookies(); // get cookies

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// start XHTML document
		out.println("<head>");
		out.println("<title>Recommendations</title>");
		out.println("</head>");

		// body section of document
		out.println("<body>");

		// if there are any cookies, recommend a book for each ISBN
		if (cookies != null && cookies.length != 0) {
			out.println("<h1>Recommendations</h1>");
			out.println("<p>");

			// get the name of each cookie
			for (int i = 0; i < cookies.length; i++) {
				out.println(cookies[i].getName() + " Unleashed. ISBN#: " + cookies[i].getValue() + "<br />");
			}

			out.println("</p>");
		} else { // there were no cookies
			out.println("<h1>No Recommendations</h1>");
			out.println("<p>You did not select a language.</p>");
		}

		out.println("<p><a href='cookie.html'>Return to cookies.html</a></p>");

		out.println("</body>");
		out.println("</html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// receive language selection and send cookie containing
		// recommended book to the client

		String language = request.getParameter("language");
		String isbn = books.get(language).toString();
		Cookie cookie = new Cookie(language, isbn);
		cookie.setMaxAge(100);
		response.addCookie(cookie); // must precede getWriter
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// send HTML page to client

		// head section of document
		out.println("<head>");
		out.println("<title>Welcome to Cookies</title>");
		out.println("</head>");

		// body section of document
		out.println("<body>");
		out.println("<p>Welcome to Cookies! You selected " + language + "</p>");

		out.println("<p><a href = " + "\"./Cookie.html\">" + "Click here to choose another language</a></p>");

		out.println("<p><a href = \"cookies2\">" + "Click here to get book recommendations</a></p>");
		out.println("</body>");

		// end XHTML document
		out.println("</html>");
		out.close(); // close stream
	}

}
