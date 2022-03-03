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
		if(path.endsWith("/register")) {//ע������
			//��������ķ�ʽ
			String method=request.getMethod();
			if("GET".equalsIgnoreCase(method)) {//ע������ͻ���ʾ
				request.getRequestDispatcher("/WEB-INF/jsp/user/register.jsp").forward(request, response);
				return;
			}else if("POST".equalsIgnoreCase(method)){
				//��ȡ�������ݣ������зǿպͺϷ�У��
				Map<String,String> errors=WebUtils.validate(request);
				if(errors!=null&&errors.size()>0) {
					//У��ʧ��
					request.setAttribute("errors", errors);
					request.getRequestDispatcher("/WEB-INF/jsp/user/register.jsp").forward(request, response);
					return;
				}
				
				//���������ռ��������ݿ��н���ע�ᴦ��
				User user=WebUtils.request2Bean(request, User.class);
				user.setUid(WebUtils.getUserUid());
				//���÷�����Ӧ���񷽷�
				userService.register(user);//��ת����ҳ��
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath()+"/index.jsp");
				return;
			}
			
			
		}else if(path.endsWith("/login")){//��¼����
			//��ȡ����ʽ
			String method=request.getMethod();
			if("POST".equalsIgnoreCase(method)) {
				//���ǿ�У��
				Map<String,String> errors=WebUtils.validate(request);
				if(errors!=null&&errors.size()>0) {
					request.setAttribute("errors", errors);
					//����ת������¼����
					request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
					return;
				}
				//��ȡ������
				String uid=request.getParameter("uid");
				String loginPwd=request.getParameter("loginPwd");
				//�������ݿ�Ϸ���У��
				User user=userService.loginUser(uid, loginPwd);
				if(user!=null) {
					//����Ƿ�ѡ�Զ���¼ѡ��
					boolean ifAutoLogin=Boolean.parseBoolean(request.getParameter("isAutoLogin"));
					if(ifAutoLogin) {//��ʾѡ��
						//�ѺϷ��û���Ϣ���浽cookie��
						Cookie cookie=new Cookie("uid",user.getUid());
						//������Ч��
						cookie.setMaxAge(30*24*60*60);//����һ����
						//������Ч����·��
						cookie.setPath("/");
						//��Cookie������ӵ�response��������
						response.addCookie(cookie);
						Cookie c2=new Cookie("loginPwd",user.getLoginPwd());
						c2.setMaxAge(30*24*60*60);
						c2.setPath("/");
						response.addCookie(c2);
					}
					//��user�������õ��Ự��
					request.getSession().setAttribute("user", user);
					response.sendRedirect(request.getContextPath()+"/index.jsp");
				}
				
				
				
			}
			
		}else if(path.endsWith("/order")) {
			//��ʾ�û�Ҫ�¶���
			Order order=null;
			//��ȡsession���б�����û���Ϣ���͹��ﳵ��Ϣ
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			ShoppingCart cart=(ShoppingCart) session.getAttribute("cart");
			if(user!=null&&cart!=null) {
				order=new Order();
				order.setOid(WebUtils.getOrderOid());
				order.setShoppingCart(cart);
				order.setUser(user);
				//���ö�������
				orderService.createMyOrder(order);
				request.setAttribute("order", order);
				//�ѵ�ǰ�û����ﳵ�е���Ϣ���
				session.removeAttribute("cart");
				request.getRequestDispatcher("/WEB-INF/jsp/order/order.jsp").forward(request, response);
				return;
			}
		}
	}
	

}
