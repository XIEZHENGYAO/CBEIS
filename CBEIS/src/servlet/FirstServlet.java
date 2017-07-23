package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.rmi.runtime.Log;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(
		urlPatterns = { "/FirstServlet" }, 
		initParams = { 
				@WebInitParam(name = "reaper", value = "abc", description = "�û���")
		})
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FirstServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        String[] titles= new String[30];//��������
        String[] newsdates= new String[30];//��������
    	int i = 0;
    	
        response.setContentType("text/html;charset=utf-8"); // ������Ӧ���ĵı����ʽ  
        PrintWriter pw = response.getWriter(); // ��ȡ response ������� 
        
        
        /* ����������һ����򵥵�ע���߼�����Ȼ�����ʵ��ҵ������൱���� */ 
		
        try {  
            Connection connect = DBUtil.getConnect();  
            Statement statement = (Statement) connect.createStatement(); // Statement�������Ϊ���ݿ����ʵ���������ݿ�����в�����ͨ������ʵ�� 
            ResultSet result;  
            
            String sqlQuery = "select * from crawler_data order by newsdate desc";
        	result = statement.executeQuery(sqlQuery);
        	
            while(result.next()){
                titles[i] = result.getString("title"); //��ѯtitleֵ
                newsdates[i] = result.getString("newsdate");//��ѯnewsdateֵ
                i++;
            }
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
          
         
        for(int j=0;j<i;j++){
        	pw.append(titles[j]+"\n");//һ��һ����������Android�˷���
        	pw.append(newsdates[j]+"\n");
        }
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
