package cn.bnt.shoppingcart.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bnt.shoppingcart.beans.User;

@WebServlet("/pay")
public class UserPayController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.�ж��û��Ƿ��¼�������¼������ҳ����ʾ�������飬���û�е�¼�ض����û���¼����
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null) {//�û�δ��¼
			//����ת��������
			request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
			return;
		}else {
			//����ת��������ҳ��
			request.getRequestDispatcher("/WEB-INF/jsp/order/orderInfo.jsp").forward(request, response);
			return;
		}
	}
	

}
