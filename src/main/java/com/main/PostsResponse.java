package com.main;

import java.util.ArrayList;

public class PostsResponse {
	private ArrayList<Post> posts;

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "PostsResponse [posts=" + posts + "]";
	}
	
	
}
