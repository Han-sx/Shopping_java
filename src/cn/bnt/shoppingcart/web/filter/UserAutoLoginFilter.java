package cn.bnt.shoppingcart.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bnt.shoppingcart.beans.User;
import cn.bnt.shoppingcart.service.UserService;
import cn.bnt.shoppingcart.utils.BeanFactoryUtils;

//ʵ���û��Զ���¼
@WebFilter(value="/*")
public class UserAutoLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;
		//��ȡ���е�COOKIE���бȶ�
		String uid=null;
		String loginPwd=null;
		Cookie[] cookies=request.getCookies();
		for(int i=0;cookies!=null&&i<cookies.length;i++) {
			String cname=cookies[i].getName();
			if(cname.equalsIgnoreCase("uid")) {
				uid=cookies[i].getValue();
				break;
			}
		}
		for(int i=0;cookies!=null&&i<cookies.length;i++) {
			String cname=cookies[i].getName();
			if(cname.equalsIgnoreCase("loginPwd")) {
				loginPwd=cookies[i].getValue();
				break;
			}
		}
		if(uid!=null&&loginPwd!=null) {
			UserService us=BeanFactoryUtils.getBean(UserService.class);
			User user=us.loginUser(uid, loginPwd);
			//�Ѹ��û����浽session����
			request.getSession().setAttribute("user", user);
		}
		chain.doFilter(request,response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}