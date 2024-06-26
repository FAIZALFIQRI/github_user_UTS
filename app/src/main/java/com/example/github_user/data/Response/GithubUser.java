package com.example.github_user.data.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUser {

	@SerializedName("total_count")
	private int totalCount;

	@SerializedName("incomplete_results")
	private boolean incompleteResults;

	@SerializedName("items")
	private List<ItemsItem> items;

	public int getTotalCount(){
		return totalCount;
	}

	public boolean isIncompleteResults(){
		return incompleteResults;
	}

	public List<ItemsItem> getItems(){
		return items;
	}
}