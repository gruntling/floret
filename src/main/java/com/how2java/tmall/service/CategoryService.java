package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.util.Page;

public interface CategoryService {
//	List<Category>list();
	public List<Category>list(Page page);
	public int getCount();
	public void add(Category category);
	public void delete(int id);
	public Category getEntity(int id);
	public void update (Category category);
}
