package cn.bnt.shoppingcart.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class BeanFactoryUtils {

	private static Map<String,String> mapBeans=new HashMap<String,String>();
	static {
		InputStream in=BeanFactoryUtils.class.getClassLoader().getResourceAsStream("beans.properties");
		Properties prop=new Properties();
		try {
			prop.load(in);
			Enumeration<String> em=(Enumeration<String>) prop.propertyNames();
			while(em.hasMoreElements()) {
				String key=em.nextElement();
				String value=prop.getProperty(key);
				mapBeans.put(key, value);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static <T> T getBean(Class clazz) {
		T bean=null;
		String className=clazz.getSimpleName();
		Set<String> keys=mapBeans.keySet();
		for(String key:keys) {
			if(key.equalsIgnoreCase(className)) {
				try {
					bean=(T) Class.forName(mapBeans.get(key)).newInstance();
					break;
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bean;
	}
}
