package com.biyi.blog.util;

public class PageBean {
	
	private int currentPage;
	
	private int pageLength;
	
	private int totalResult = -1;
	
	private int startIndex;
	
	private int totalPage;
	
	private void count(){
		int lastResult = totalResult % pageLength;
		if(lastResult == 0){
			totalPage = totalResult / pageLength;
		}else{
			totalPage = (totalResult / pageLength) + 1;
		}
		if(currentPage > totalPage){
			currentPage = totalPage;
		}
		if(currentPage <= 0){
			currentPage = 1;
		}
		startIndex = (currentPage - 1) * pageLength;
	}
	
	private void countWrap(){
		if(totalResult >= 0){
			count();
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		countWrap();
	}

	public int getPageLength() {
		return pageLength;
	}

	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
		countWrap();
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
		countWrap();
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getTotalPage() {
		return totalPage;
	}
	
}
