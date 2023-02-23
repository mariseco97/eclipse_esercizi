package it.ntt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EuroDollaro
 */
@WebServlet("/EuroDollaro")
public class EuroDollaro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EuroDollaro() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String euro = request.getParameter("euro"); //legge il parametro euro dalla richiesta
		if(euro== null || euro.trim().isEmpty() ) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<title>Errore</title>");
			out.println("<body>");
			out.println("<h1>Hai richiesto un parametro scorretto o nullo</h1>");
			out.println("</body>");
			out.println("</html>");
			out.println(request.getQueryString());
			
		}else {
			double euro_daconvertire = Double.parseDouble(euro);
			double dollaro = 1.06*euro_daconvertire; 
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<title>Conversione euro dollaro</title>");
			out.println("<body>");
			out.println("<h1>L'euro: " + euro_daconvertire + " è in dollari: " + dollaro + "</h1>");
			out.println("</body>");
			out.println("</html>");
			out.println(request.getQueryString());
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
