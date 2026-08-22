package com.hamza.booking.admin.widget.model;

import java.util.List;

/**
 * POJO representing the test response from the API.
 * 
 * @author hamza
 */
public class TestResponse {

	private Object actions;
	private List<Object> facets;
	private List<TestItem> items;
	private int lastPage;
	private int page;
	private int pageSize;
	private int totalCount;

	public Object getActions() {
		return actions;
	}

	public void setActions(Object actions) {
		this.actions = actions;
	}

	public List<Object> getFacets() {
		return facets;
	}

	public void setFacets(List<Object> facets) {
		this.facets = facets;
	}

	public List<TestItem> getItems() {
		return items;
	}

	public void setItems(List<TestItem> items) {
		this.items = items;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}

