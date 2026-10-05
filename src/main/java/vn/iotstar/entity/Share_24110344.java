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
@Table(name = "Shares")
public class Share_24110344 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ShareId")
	private Integer shareId;

	@Column(name = "Emails", length = 50)
	private String emails;

	@Column(name = "SharedDate")
	private LocalDate sharedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Username")
	private User_24110344 user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VideoId")
	private Video_24110344 video;

	public Share_24110344() {
	}

	public Share_24110344(String emails, LocalDate sharedDate, User_24110344 user, Video_24110344 video) {
		this.emails = emails;
		this.sharedDate = sharedDate;
		this.user = user;
		this.video = video;
	}

	public Integer getShareId() {
		return shareId;
	}

	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public LocalDate getSharedDate() {
		return sharedDate;
	}

	public void setSharedDate(LocalDate sharedDate) {
		this.sharedDate = sharedDate;
	}

	public User_24110344 getUser() {
		return user;
	}

	public void setUser(User_24110344 user) {
		this.user = user;
	}

	public Video_24110344 getVideo() {
		return video;
	}

	public void setVideo(Video_24110344 video) {
		this.video = video;
	}
}