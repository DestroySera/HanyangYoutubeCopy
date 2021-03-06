package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import dao.FileDAO;
import dao.MemberDAO;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/FileUpload")
@MultipartConfig(maxFileSize = 1024 * 1024 * 12, 
	location = "/usr/lib/download/tomcat8/webapps/ROOT/temp", 
	fileSizeThreshold = 1024 * 1024 * 512)
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
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
		
		request.setCharacterEncoding("UTF-8");
		
		Part part = request.getPart("file");
		Part part2 = request.getPart("file2");
		String owner = request.getParameter("video_owner");
		String name = request.getParameter("video_name");
		String intro = request.getParameter("video_intro");
		
		
		
		
		String dir = "/usr/lib/download/tomcat8/webapps/ROOT/file/";
		String uniqueFileName = UUID.randomUUID().toString().replace("-", "");
		try {
			part.write(dir + uniqueFileName+".mp4");
			part2.write(dir + uniqueFileName+".png");
			System.out.println(dir + uniqueFileName+".mp4");
			System.out.println(dir + uniqueFileName+".png");
			
			FileDAO.UploadFile(uniqueFileName, owner, dir, name, intro);
			
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('?????? ????????? ??????.');");
			out.print("location.href='/;");
			out.print("</script>");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('?????? ????????? ??????.');");
			out.print("location.href='/index.jsp';");
			out.print("</script>");
			out.close();	
		}
		
	}

	
/*    private String getFilename(Part part){
        String fileName = "";        
        String header = part.getHeader("content-disposition");
        //part.getHeader :form-data; name="theFile";
        //filename="RHDSetup.log" ?????? ????????? ?????? ????????? ?????????????????? ????????? ??? ???????????? ??????
        String [] elements = header.split(";");
       
        for(String element : elements){
            System.out.println("??????????????? ???:" +element);
            fileName = element.substring(element.indexOf('=')+1);
            System.out.println("?????? ???:"+fileName);
            fileName = fileName.trim().replace("/","-"); // " <- ???????????? ??????
            System.out.println("?????? ???:"+fileName);
        }        
        return fileName;        
    }*/
}
