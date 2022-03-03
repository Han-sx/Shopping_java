package cn.bnt.shoppingcart.beans;

public class Item {//���԰Ѹ���Ŀ������ɹ���ͼ���һ���嵥

	private int id;//��ʾ��Ŀ�ı��
	private Product product;//��ʾ����Ŀ����Ӧ��ͼ��
	private int number=1;//��ʾ�����ͼ�������,Ĭ��Ϊ1
	private Double sumMoney;//��ʾ����Ŀ���ܽ��
	//�ṩ��Ӧ��getter/setter����
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Double getSumMoney() {
		this.sumMoney=0.0;
		//ÿ����Ŀ���ܽ��=��Ʒ�ļ۸�*��Ʒ������
		this.sumMoney=this.getNumber()*this.getProduct().getPrice();
		return sumMoney;
	}
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(int id, Product product, int number) {
		super();
		this.id = id;
		this.product = product;
		this.number = number;
	}
	
}
