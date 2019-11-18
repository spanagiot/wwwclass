
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.*;



public class Register extends javax.servlet.http.HttpServlet {
    private ORMUser ormUser;

    public void init(){
        this.ormUser = new ORMUser();
    }
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        StringBuffer jb = new StringBuffer();

        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { System.out.println(e);return; }


        JSONObject jsonObject;
        try {
            jsonObject = FormParser.ToJSON(jb.toString());
            System.out.println(jsonObject);
        } catch (Exception e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
        }
        try {
            if(this.ormUser.getUser(jsonObject.get("username").toString()) != null){
                request.setAttribute("message", "Username already exists");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }else{
                String securePassword = Hasher.generateStrongPasswordHash(jsonObject.get("password").toString());
                ormUser.addUser(new User(jsonObject.get("username").toString(), securePassword));
            }
        }catch (Exception e) {
            System.out.println(e);
            response.setStatus(500);
            return;
        }

        response.sendRedirect("./register-success.jsp");


        // Work with the data using methods like...
        // int someInt = jsonObject.getInt("intParamName");
        // String someString = jsonObject.getString("stringParamName");
        // JSONObject nestedObj = jsonObject.getJSONObject("nestedObjName");
        // JSONArray arr = jsonObject.getJSONArray("arrayParamName");
        // etc...
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }
}
