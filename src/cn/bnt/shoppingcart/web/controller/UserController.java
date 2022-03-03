package cn.bnt.shoppingcart.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bnt.shoppingcart.beans.Order;
import cn.bnt.shoppingcart.beans.ShoppingCart;
import cn.bnt.shoppingcart.beans.User;
import cn.bnt.shoppingcart.service.OrderService;
import cn.bnt.shoppingcart.service.UserService;
import cn.bnt.shoppingcart.utils.BeanFactoryUtils;
import cn.bnt.shoppingcart.utils.WebUtils;

@WebServlet("/user/*")
public class UserController extends HttpServlet {
	
	private UserService userService=BeanFactoryUtils.getBean(UserService.class);
	private OrderService orderService=BeanFactoryUtils.getBean(OrderService.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getRequestURI();
		if(path.endsWith("/register")) {//注册请求
			//分析请求的方式
			String method=request.getMethod();
			if("GET".equalsIgnoreCase(method)) {//注册表单给客户显示
				request.getRequestDispatcher("/WEB-INF/jsp/user/register.jsp").forward(request, response);
				return;
			}else if("POST".equalsIgnoreCase(method)){
				//获取表单的数据，并进行非空和合法校验
				Map<String,String> errors=WebUtils.validate(request);
				if(errors!=null&&errors.size()>0) {
					//校验失败
					request.setAttribute("errors", errors);
					request.getRequestDispatcher("/WEB-INF/jsp/user/register.jsp").forward(request, response);
					return;
				}
				
				//进行数据收集，到数据库中进行注册处理
				User user=WebUtils.request2Bean(request, User.class);
				user.setUid(WebUtils.getUserUid());
				//调用服务层对应服务方法
				userService.register(user);//跳转到主页面
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath()+"/index.jsp");
				return;
			}
			
			
		}else if(path.endsWith("/login")){//登录请求
			//获取请求方式
			String method=request.getMethod();
			if("POST".equalsIgnoreCase(method)) {
				//表单非空校验
				Map<String,String> errors=WebUtils.validate(request);
				if(errors!=null&&errors.size()>0) {
					request.setAttribute("errors", errors);
					//请求转发到登录界面
					request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
					return;
				}
				//获取表单内容
				String uid=request.getParameter("uid");
				String loginPwd=request.getParameter("loginPwd");
				//进行数据库合法性校验
				User user=userService.loginUser(uid, loginPwd);
				if(user!=null) {
					//检查是否勾选自动登录选项
					boolean ifAutoLogin=Boolean.parseBoolean(request.getParameter("isAutoLogin"));
					if(ifAutoLogin) {//表示选中
						//把合法用户信息保存到cookie中
						Cookie cookie=new Cookie("uid",user.getUid());
						//设置有效期
						cookie.setMaxAge(30*24*60*60);//保存一个月
						//设置有效访问路径
						cookie.setPath("/");
						//把Cookie对象添加到response对象中来
						response.addCookie(cookie);
						Cookie c2=new Cookie("loginPwd",user.getLoginPwd());
						c2.setMaxAge(30*24*60*60);
						c2.setPath("/");
						response.addCookie(c2);
					}
					//把user对象设置到会话域
					request.getSession().setAttribute("user", user);
					response.sendRedirect(request.getContextPath()+"/index.jsp");
				}
				
				
				
			}
			
		}else if(path.endsWith("/order")) {
			//表示用户要下订单
			Order order=null;
			//获取session域中保存的用户信息，和购物车信息
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			ShoppingCart cart=(ShoppingCart) session.getAttribute("cart");
			if(user!=null&&cart!=null) {
				order=new Order();
				order.setOid(WebUtils.getOrderOid());
				order.setShoppingCart(cart);
				order.setUser(user);
				//调用订单方法
				orderService.createMyOrder(order);
				request.setAttribute("order", order);
				//把当前用户购物车中的信息清空
				session.removeAttribute("cart");
				request.getRequestDispatcher("/WEB-INF/jsp/order/order.jsp").forward(request, response);
				return;
			}
		}
	}
	

}
