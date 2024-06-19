package in.co.online.vote.bean;

import java.sql.Blob;


public class PartiesBean extends BaseBean {
	
	
	private String name;
	
	private Blob image;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	@Override
	public String getKey() {
		return id+ "";
	}

	@Override
	public String getValue() {
		return name;
	}

}
