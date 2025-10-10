import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        File file = new File("users.txt");
        if (!file.exists()) {
            out.println("<h3 style='color:red;'>No users found. Please register first.</h3>");
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        boolean found = false;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                found = true;
                break;
            }
        }
        reader.close();

        if (found) {
            response.sendRedirect("home.html");
        } else {
            out.println("<h3 style='color:red;'>Invalid username or password!</h3>");
        }
    }
}

