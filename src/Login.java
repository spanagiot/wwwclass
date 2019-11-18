import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet(name = "Login")
public class Login extends HttpServlet {
    private ORMUser ormUser;

    public void init(){
        this.ormUser = new ORMUser();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { System.out.println(e);return; }

        JSONObject jsonObject;
        User user;
        try {
            jsonObject = FormParser.ToJSON(jb.toString());
            System.out.println(jsonObject);
        } catch (Exception e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
        }
        try {
            // Fetch the user from the db
            user = ormUser.getUser(jsonObject.get("username").toString());
        }catch (Exception e) {
            System.out.println(e);
            response.setStatus(500);
            return;
        }
        try {
            // validate password
            if (user != null && Hasher.validatePassword(jsonObject.get("password").toString(), user.getPassword())) {
                Cookie ck = new Cookie("auth", jsonObject.get("username").toString());
                ck.setMaxAge(1200);
                response.addCookie(ck);
            } else {
                request.setAttribute("message", "Wrong password");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }catch(Exception e){
            System.out.println(e);
            return;
        }
        response.sendRedirect("./index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
