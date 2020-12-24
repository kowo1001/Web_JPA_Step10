package probono.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import probono.exception.MessageException;
import probono.model.ActivistDAO;
import probono.model.ProbonoService;
import probono.model.domain.Activist;
import probono.model.dto.ActivistDTO;

@WebServlet("/probono")
@Slf4j
public class ProbonoFrontController extends HttpServlet {
    public ProbonoFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String command = request.getParameter("command");
		
		if(command != null) {
			try{
				if(command.equals("probonoProjectAll")){//모든 probono project 정보 검색
					probonoProjectAll(request, response);
				}else if(command.equals("activistAll")){//모든 재능 기부자 검색
					activistAll(request, response);
				}else if(command.equals("activist")){//특정 재능 기부자 정보 검색
					activist(request, response);
				}else if(command.equals("activistInsert")){//재능 기부자 추가 등록
					activistInsert(request, response);
				}else if(command.equals("activistUpdateReq")){//재능 기부자 정보 수정요청
					activistUpdateReq(request, response);
				}else if(command.equals("activistUpdate")){//재능 기부자 정보 수정
					activistUpdate(request, response);
				}else if(command.equals("activistDelete")){//재능 기부자 탈퇴[삭제]
					activistDelete(request, response);
				}
			}catch(Exception s){
				request.setAttribute("errorMsg", s.getMessage());
				request.getRequestDispatcher("showError.jsp").forward(request, response);
				s.printStackTrace();
			}
		}else {
			response.sendRedirect("index.html");
		}
	}
	

	//모두 ProbonoProject 검색 메소드
	public void probonoProjectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.getSession().setAttribute("probonoProjectAll", ProbonoService.getAllProbonoProjects());
			log.info("모든 ProbonoProject 검색");
			url = "probonoProjectList.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			log.info("해당하는 ProbonoProject가 존재하지 않습니다");
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	//모든 재능 기부자 검색 - 검색된 데이터 출력 화면[activistList.jsp]
	public void activistAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.getSession().setAttribute("allActivist",ProbonoService.getAllActivists());
			log.info("모든 재능 기부자 검색");
			url = "activistDetail.jsp";
		}catch(Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			log.info("모든 재능 기부자 검색시 에러 발생");
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	//재능 기부자 검색 
	public void activist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.getSession().setAttribute("activist", ProbonoService.getActivist(request.getParameter("activistId")));
			log.info("특정 재능 기부자 검색");
			url = "activistList.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			log.info("특정 재능 기부자 검색시 에러 발생");
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	

	//재능 기부자 가입 메소드
	protected void activistInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessageException {
		String url = "showError.jsp";
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String major = request.getParameter("major");
		
		
		//해킹등으로 불합리하게 요청도 될수 있다는 가정하에 모든 데이터가 제대로 전송이 되었는지를 검증하는 로직 : null check
		if(id != null && id.length() !=0 && name != null) {
			request.getSession().setAttribute("successMsg", "id와 name값이 존재합니다");
		}else {
			throw new MessageException("id와 name을 입력해주세요.");
		}
		
		
		ActivistDTO activist = new ActivistDTO(id, name, pw, major);
		try{
			boolean result = ProbonoService.addActivist(id, name, pw, major);
			if(result){
				request.getSession().setAttribute("activist", activist);
				request.setAttribute("successMsg", "가입 완료");
				log.info("재능 기부자 가입 완료되었습니다");
				url = "activistDetail.jsp";
			}else{
				request.setAttribute("errorMsg", "다시 시도하세요");
				log.info("재능 기부자 등록을 재시도해주세요");
			}
		}catch (Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			log.info("재능 기부자 등록시 에러 발생");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	//재능 기부자 수정 요구
	public void activistUpdateReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.getSession().setAttribute("activist", ProbonoService.getActivist(request.getParameter("activistId")));
			log.info("재능 기부자 수정 요청");
			url = "activistUpdate.jsp";
		}catch (Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			log.info("재능 기부자 수정 요청시 에러 발생");
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	//재능 기부자 수정 - 상세정보 확인 jsp[activistDetail.jsp]
	public void activistUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		try {
			Activist activist =(Activist) request.getSession().getAttribute("activist");
			activist.setActivistid(request.getParameter("activistId").trim());
			activist.setMajor(request.getParameter("major").trim());
			ActivistDAO.updateActivist(activist.getActivistid(), activist.getMajor());
			log.info("재능 기부자 수정완료되었습니다");
			url = "activistDetail.jsp";
		
		}catch (SQLException e) {
			request.setAttribute("errorMsg", "업데이트 실패");
			log.info("수정하고자 하는 재능 기부자가 존재하지 않습니다");
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	//재능 기부자 삭제
	public void activistDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
	
		try {
			request.getSession().setAttribute("activist", ProbonoService.getActivist(request.getParameter("activistId")));
			Activist activist =(Activist) request.getSession().getAttribute("activist");
			ActivistDAO.deleteActivist(activist.getActivistid());
			log.info("재능 기부자 삭제완료되었습니다");
			url = "activistDetail.jsp";
			
		} catch (Exception e) {
			request.setAttribute("errorMsg", "기부자 삭제시 에러");
			log.info("재능 기부자 삭제시 에러 발생");
			e.printStackTrace();
		}
	}
}
