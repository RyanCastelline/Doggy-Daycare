package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dog;

/**
 * Servlet implementation class UpdateDogServlet
 */
@WebServlet("/UpdateDogServlet")
public class UpdateDogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		DogDao dao = new DogDao();
		String name = request.getParameter("name");
        Integer owner = Integer.parseInt(request.getParameter("ownerId"));
        Integer playground = Integer.parseInt(request.getParameter("playgroundId"));
        String breed = request.getParameter("breed");
		String gender = request.getParameter("gender");
		String registrationDate = request.getParameter("registrationDate");
		boolean vaccinations = Boolean.parseBoolean(request.getParameter("vaccinations"));
		String lastVisit = request.getParameter("lastVisit");
		boolean active = Boolean.parseBoolean(request.getParameter("activeFlag"));
        SimpleDateFormat rDate = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date registration = null;
		try {
			registration = rDate.parse(registrationDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        SimpleDateFormat lVisit = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date last = null;
		try {
			last = lVisit.parse(lastVisit);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer tempId = Integer.parseInt(request.getParameter("dogId"));
		Dog dogToUpdate = dao.searchForDogById(tempId);
		
		dogToUpdate.setName(name);
		dogToUpdate.setOwnerId(owner);
		dogToUpdate.setTodaysPlayground(playground);
		dogToUpdate.setBreed(breed);
		dogToUpdate.setGender(gender);
		dogToUpdate.setRegistrationDate(dogToUpdate.convertJavaToSqlDate(registration));
		dogToUpdate.setVaccinations(vaccinations);
		dogToUpdate.setLastVisit(dogToUpdate.convertJavaToSqlDate(last));
		dogToUpdate.setActiveFlag(active);
		
		dao.updateDog(dogToUpdate);
		
		getServletContext().getRequestDispatcher("/updateDog.jsp").forward(request, response);
	}

}
