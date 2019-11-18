import com.sun.tools.internal.ws.wsdl.document.http.HTTPUrlEncoded;
import org.json.simple.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class FormParser {
    static public JSONObject ToJSON(String formData) throws UnsupportedEncodingException

    {
        String[] formElements = formData.split("&");
        JSONObject res = new JSONObject();

        for(int i = 0; i < formElements.length; i++){
            String[] element = formElements[i].split("=");
            res.put(java.net.URLDecoder.decode(element[0],StandardCharsets.UTF_8.name()),java.net.URLDecoder.decode(element[1],StandardCharsets.UTF_8.name()));
        }

        return res;

    }
}
