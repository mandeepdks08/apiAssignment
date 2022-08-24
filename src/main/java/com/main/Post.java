package com.main;

import lombok.Getter;
import lombok.Setter;

public class Post {
	@Getter @Setter private String id;
	@Getter @Setter private String userId;
	@Getter @Setter private String body;
	@Getter @Setter private String email;
	@Getter @Setter private String title;
	@Getter @Setter private String name;
	@Getter @Setter private String postId;

	@Override
	public String toString() {
		return "Post [id=" + id + ", userId=" + userId + ", body=" + body + ", email=" + email + ", title=" + title
				+ ", name=" + name + ", postId=" + postId + "]";
	}

}
