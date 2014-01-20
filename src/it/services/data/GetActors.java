package it.services.data;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.db.*;
import com.services.convert.toXML.*;
/**
 * Servlet implementation class GetActors
 */
@WebServlet("/GetActors")
public class GetActors extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectService connect_instance;
    private ToXML objt = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetActors() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.connect_instance = new ConnectService("sakila");
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			name = name.trim().toLowerCase();
			ResultSet rs = this.connect_instance.executeQuery("select * from actor where first_name LIKE '"+name+"%'");
			String xmlresp = new ToXML(rs).XML_to_String(rs);
			System.out.println(xmlresp);
			response.setContentType("text/xml"); 
			response.setHeader("Cache-Control", "no-cache"); 
			response.getWriter().write(xmlresp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
