package com.how2java.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.tmall.mapper.CategoryMapper;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.Page;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryMapper categoryMapper;
//	public List<Category>list(){
//		return categoryMapper.list();
//	}
	
	public List<Category>list(Page page){
		return categoryMapper.list(page);
	}
	
	public int getCount(){
		return categoryMapper.total();
	}
	
	public void add(Category category){
		categoryMapper.add(category);
	}
	public void delete(int id){
		categoryMapper.delete(id);
	}
	public Category getEntity(int id){
		return categoryMapper.getEntity(id);
	}
	public void update(Category category){
		 categoryMapper.update(category);
	}
}
