package cn.bnt.shoppingcart.dao;

import java.util.List;

import cn.bnt.shoppingcart.beans.Product;

public interface ProductDao {

	//�����ݿ��г־û�������һ����¼��һ��ͼ����Ʒ
	public boolean save(Product p);
	//ͨ��ͼ��������ѯһ��ͼ�����Ϣ
	public Product findByPid(String pid);
	//��ѯ�����е�ͼ��
	public List<Product> findAll();
	//��ҳ��ʾ���ݣ�����pageNum��ʾҪ��ʾ�ڼ�ҳ���ݣ�pageSize��ʾÿҳ��ʾ��������¼
	public List<Product> queryForPage(int pageNum,int pageSize);
	//����һ��ͼ�����Ϣ
	public void update(Product p);
	//ͨ��ͼ������ɾ��һ��ͼ����Ϣ(��Ʒ�¼�)
	public void deleteByPid(String pid);
	
	public int totalPages(int pageSize);
}
