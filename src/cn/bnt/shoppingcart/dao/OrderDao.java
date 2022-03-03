package cn.bnt.shoppingcart.dao;

import java.util.List;

import cn.bnt.shoppingcart.beans.Order;

public interface OrderDao {

	public void save(Order order);
	//ͨ���û��������ѯ���û����µĶ���
	public List<Order> queryForUser(String uid);
	//ͨ�������������ѯ�ö�������Ϣ
	public Order queryForOid(String oid);
	//��ѯ�������飨�����ö���������Щͼ�飩
	public Order queryInfoForOid(String oid);
	
	
}
