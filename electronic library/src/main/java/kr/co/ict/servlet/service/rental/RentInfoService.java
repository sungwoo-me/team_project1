package kr.co.ict.servlet.service.rental;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ict.RentalDAO;
import kr.co.ict.RentalVO;
import kr.co.ict.UserDAO;
import kr.co.ict.UserVO;
import kr.co.ict.servlet.service.review.IReviewService;

public class RentInfoService implements IRentalService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 세션 아이디
				HttpSession session = request.getSession();
				String sId = (String)session.getAttribute("sId");
				
				// 다오 생성, 메서드 호출
				RentalDAO dao1 = RentalDAO.getInstance();
				UserDAO dao2 = UserDAO.getInstance();
				List<RentalVO> rentInfoList = dao1.getAllRentalInfoBookList(sId);
				UserVO userInfo = dao2.getUserData(sId);
				System.out.println(userInfo);
				System.out.println(rentInfoList);
				// 바인딩
				request.setAttribute("rentInfoList", rentInfoList);
				request.setAttribute("userInfo", userInfo);
		
	}

}
