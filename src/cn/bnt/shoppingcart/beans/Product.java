package cn.bnt.shoppingcart.beans;

public class Product {

	private String pid;//��ʾͼ����
	private String name;//ͼ������
	private Double price;//ͼ��ļ۸�
	private String imagePath;//��ʾ��Ÿ�ͼ��ͼƬ��·��
	private String pdesc;//��ʾͼ�������
	//�ṩ��Ӧ��getter/setter������Ҳ��ΪJavaBean�淶��
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String pid, String name, Double price, String imagePath, String pdesc) {
		super();
		this.pid = pid;
		this.name = name;
		this.price = price;
		this.imagePath = imagePath;
		this.pdesc = pdesc;
	}
	
}