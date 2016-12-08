package com.niit.dao;

import java.util.List;

import com.niit.model.Category;

public interface CategoryDao 
{
	public String list();
	public Category get(String id);
	public void saveorUpdate(Category category);
	public void delete(String id);

}
