package cn.bnt.shoppingcart.beans;

public class Product {

	private String pid;//表示图书编号
	private String name;//图书名称
	private Double price;//图书的价格
	private String imagePath;//表示存放该图书图片的路径
	private String pdesc;//表示图书的描述
	//提供对应的getter/setter方法（也称为JavaBean规范）
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
