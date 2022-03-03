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
		//1.判断用户是否登录，如果登录，则用页面显示订单详情，如果没有登录重定向到用户登录界面
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null) {//用户未登录
			//请求转发来处理
			request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
			return;
		}else {
			//请求转发到订单页面
			request.getRequestDispatcher("/WEB-INF/jsp/order/orderInfo.jsp").forward(request, response);
			return;
		}
	}
	

}
