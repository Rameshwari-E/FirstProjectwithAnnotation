package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.niit.dao.CategoryDao;
import com.niit.dao.CategoryDaoImpl;
import com.niit.model.Category;

@Controller
public class AdminController 
{
	@Autowired
	CategoryDao categroyDAOImpl;

	
	@RequestMapping("/Admin")
	public ModelAndView AdminHomeData()
	{
		return new ModelAndView("AdminHomePage");
	}
	
	
	
	@RequestMapping(value="/categorygson")
	@ResponseBody
	public String CategoryGson()
	{
		String  list=categroyDAOImpl.list();
		
		return list;
	}

	@RequestMapping(value = { "category"})
	public ModelAndView CategoryPage(Model model) {
		model.addAttribute("category", new Category());
		
		model.addAttribute("categoryList", categroyDAOImpl.list());
		model.addAttribute("CategoryPageClicked", "true");
		
		String list=categroyDAOImpl.list();
		
		 
		return new ModelAndView("AdminCategory","data",list);
	}
	@RequestMapping(value = { "addcategory", "editcategory/addcategory" }, method = RequestMethod.POST)
	public String addCategory(@ModelAttribute("category") Category category) {
		categroyDAOImpl.saveorUpdate(category);
		return "redirect:/category";
	}
	@RequestMapping("editcategory/{id}")
	public String editCategory(@PathVariable("id") String id, Model model) {
		System.out.println("editCategory");
		model.addAttribute("category", this.categroyDAOImpl.get(id));
		model.addAttribute("categoryList", categroyDAOImpl.list());
		model.addAttribute("CategoryPageClicked", "true");
		model.addAttribute("EditCategory", "true");
		return "redirect:/category";
	}
	@RequestMapping(value = { "removecategory/{id}", "editcategory/removecategory/{id}" })
	public String removeCategory(@PathVariable("id") String id, Model model) throws Exception {
		categroyDAOImpl.delete(id);
		model.addAttribute("message", "Successfully Deleted");
		return "redirect:/category";
	}

	

}
