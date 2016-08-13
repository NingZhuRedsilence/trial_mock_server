import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by barnett on 8/9/16.
 */
public class RepairServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.getWriter().println("note: the real Pliny service does not process code on the GET method.  This is just for debugging of the mock service itself.");
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println(request.getParameterMap());

        String requestType = request.getParameter("req_type");
        String functionName = request.getParameter("func_name");
        String code = request.getParameter("code");
        System.out.println("code is: " + code);

        response.setContentType("text");
        response.setStatus(HttpServletResponse.SC_OK);

        if(requestType == null || functionName == null || code == null)
        {
            response.getWriter().println("{ \t\"msg\" : \"Invalid input data.\", \n\t\"succeded\" : false \n}");
            return;
        }
        else if(requestType.equals("repair"))
        {
            String repairedCode = code.toUpperCase();
            repairedCode = repairedCode.replace("\\", "\\\\");
            repairedCode = repairedCode.replace("\b", "\\b");
            repairedCode = repairedCode.replace("\f", "\\f");
            repairedCode = repairedCode.replace("\n", "\\n");
            repairedCode = repairedCode.replace("\r", "\\r");
            repairedCode = repairedCode.replace("\t", "\\t");
            repairedCode = repairedCode.replace("\"", "\\");

            response.getWriter().println(
                    "{" + "\n" +
                            "\t\"completed_code\" : \"" + repairedCode + "\"," + "\n" +
                            "\t\"msg\" : \"Success.\"," + "\n" +
                            "\t\"console\" : \"some logging message\"," + "\n" +
                            "\t\"succeeded\" : true" + "\n" +
                            "}");

            System.out.println("after mock server transformation: " + repairedCode);
            return;
        }
        else
        {
            response.getWriter().println(
                    "{" + "\n" +
                            "\t\"fixed_code\" : \"\"," + "\n" +
                            "\t\"test_result\" : \"\"," + "\n" +
                            "\t\"template\" : \"\"," + "\n" +
                            "\t\"gen_template_prog\" : \"\"," + "\n" +
                            "\t\"diff\" : \"\"," + "\n" +
                            "\t\"msg\" : \"Invalid request.\"," + "\n" +
                            "\t\"good_progs\" : []," + "\n" +
                            "\t\"loc\" : -1," + "\n" +
                            "\t\"succeeded\" : false" + "\n" +
                            "}");
            return;
        }
    }
}
