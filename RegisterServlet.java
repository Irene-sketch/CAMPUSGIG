import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (!password.equals(confirmPassword)) {
            out.println("<h3 style='color:red;'>Passwords do not match!</h3>");
            return;
        }

        File file = new File(getServletContext().getRealPath("/") + "users.txt");
        if (!file.exists()) file.createNewFile();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        boolean userExists = false;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0 && parts[0].equals(username)) {
                userExists = true;
                break;
            }
        }
        reader.close();

        if (userExists) {
            out.println("<h3 style='color:red;'>Username already exists. Try another one.</h3>");
        } else {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(username + "," + password);
            writer.newLine();
            writer.close();

            // ✅ Redirect to login page after registration
            response.sendRedirect("login1.html");
        }
    }
}
