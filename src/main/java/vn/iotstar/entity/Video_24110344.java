package vn.iotstar.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Videos")
public class Video_24110344 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "VideoId", length = 50, nullable = false)
	private String videoId;

	@Column(name = "Title", columnDefinition = "NVARCHAR(200)")
	private String title;

	@Column(name = "Poster", columnDefinition = "NVARCHAR(500)")
	private String poster;

	@Column(name = "Views")
	private Integer views;

	@Column(name = "Price", precision = 18, scale = 0, nullable = false)
	private BigDecimal price = BigDecimal.ZERO;

	@Column(name = "Description", columnDefinition = "NVARCHAR(500)")
	private String description;

	@Column(name = "Active")
	private Boolean active;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CategoryId")
	private Category_24110344 category;

	public Video_24110344() {
	}

	public Video_24110344(String videoId, String title, String poster, Integer views, String description,
			Boolean active, Category_24110344 category) {

		this.videoId = videoId;
		this.title = title;
		this.poster = poster;
		this.views = views;
		this.description = description;
		this.active = active;
		this.category = category;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Category_24110344 getCategory() {
		return category;
	}

	public void setCategory(Category_24110344 category) {
		this.category = category;
	}
}