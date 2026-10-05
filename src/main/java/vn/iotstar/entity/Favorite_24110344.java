package vn.iotstar.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Favorites")
public class Favorite_24110344 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FavoriteId")
	private Integer favoriteId;

	@Column(name = "LikedDate")
	private LocalDate likedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VideoId")
	private Video_24110344 video;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Username")
	private User_24110344 user;

	public Favorite_24110344() {
	}

	public Favorite_24110344(LocalDate likedDate, Video_24110344 video, User_24110344 user) {
		this.likedDate = likedDate;
		this.video = video;
		this.user = user;
	}

	public Integer getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
	}

	public LocalDate getLikedDate() {
		return likedDate;
	}

	public void setLikedDate(LocalDate likedDate) {
		this.likedDate = likedDate;
	}

	public Video_24110344 getVideo() {
		return video;
	}

	public void setVideo(Video_24110344 video) {
		this.video = video;
	}

	public User_24110344 getUser() {
		return user;
	}

	public void setUser(User_24110344 user) {
		this.user = user;
	}
}