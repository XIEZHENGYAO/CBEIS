package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContentServlet
 */
@WebServlet("/ContentServlet")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url= "";//֪ͨԭ��ҳ
        String content = "";//֪ͨ����
        
        response.setContentType("text/html;charset=utf-8"); // ������Ӧ���ĵı����ʽ  
        PrintWriter pw = response.getWriter(); // ��ȡ response ������� 
        
        String number = request.getParameter("number");
        
        try {  
            Connection connect = DBUtil.getConnect();  
            Statement statement = (Statement) connect.createStatement();  
            ResultSet result;  
            
            String sqlQuery = "select * from crawler_data order by newsdate desc limit " +number +",1";
            result = statement.executeQuery(sqlQuery);//ִ�в�ѯ��ǰ���еļ�¼��
            
            if(result.next()){
            	url = result.getString("url");
            	content = result.getString("summary");
            }           
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
          
 
        pw.append(url + "\n");
        pw.append(content);
        pw.flush();  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
