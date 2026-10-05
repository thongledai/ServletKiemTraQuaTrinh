package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Category")
public class Category_24110344 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryId")
	private Integer categoryId;

	@Column(name = "Categoryname", length = 100)
	private String categoryname;

	@Column(name = "Categorycode", length = 100)
	private String categorycode;

	@Column(name = "Images", length = 500)
	private String images;

	@Column(name = "Status")
	private Boolean status;
	@Transient
	private long videoCount;

	public long getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(long videoCount) {
		this.videoCount = videoCount;
	}

	public Category_24110344() {
	}

	public Category_24110344(String categoryname, String categorycode, String images, Boolean status) {
		this.categoryname = categoryname;
		this.categorycode = categorycode;
		this.images = images;
		this.status = status;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getCategorycode() {
		return categorycode;
	}

	public void setCategorycode(String categorycode) {
		this.categorycode = categorycode;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}