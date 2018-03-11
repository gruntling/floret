package com.how2java.tmall.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page;
import com.how2java.tmall.util.UploadedImageFile;


@Controller
@RequestMapping("")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("admin_category_list")
	public String list(Model model,Page page){
		List<Category>cs=categoryService.list(page);
		int total=categoryService.getCount();
		page.setTotal(total);
		model.addAttribute("cs", cs);
		model.addAttribute("page", page);
		return "admin/listCategory";
	}
	
	 @RequestMapping("admin_category_add")
	    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
	        System.out.println(c.getId());
	        System.out.println(c.getName()+"           name");
	        categoryService.add(c);
	        System.out.println(c.getId());
	        File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
	        File file = new File(imageFolder,c.getId()+".jpg");
	        if(!file.getParentFile().exists())
	            file.getParentFile().mkdirs();
	        System.out.println(uploadedImageFile);
	        System.out.println(uploadedImageFile.getImage());
	        System.out.println(file);
	        uploadedImageFile.getImage().transferTo(file);
	        BufferedImage img = ImageUtil.change2jpg(file);
	        ImageIO.write(img, "jpg", file);
	 
	        return "redirect:/admin_category_list";
	    }
	 
	 @RequestMapping("admin_category_delete")
	 public String delete(HttpSession session,String id){
		 File file = new File(session.getServletContext().getRealPath("img/category"));
		 File files = new File(file+id+".jpg");//删除图片需要再看看
		 files.delete();
		 System.out.println(id);
		 categoryService.delete(Integer.parseInt(id));
		 return "redirect:/admin_category_list";
	 }
	 
	 @RequestMapping("admin_category_edit")
	 public String goEdit(int id,Model model,Category category){
		 if(category.getId() != null){
			 category =	categoryService.getEntity(id);
		 }
		 model.addAttribute("c", category);
		 return "admin/editCategory";
	 }
	 
	 @RequestMapping("admin_category_update")
	 public String update(Category category,HttpSession session,UploadedImageFile uploadedImageFile)throws IOException{
		 categoryService.update(category);
		
		   MultipartFile image = uploadedImageFile.getImage();
	        if(null!=image &&!image.isEmpty()){
	            File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
	            System.out.println(session.getServletContext().getRealPath("img/category"));
	            File file = new File(imageFolder,category.getId()+".jpg");
	            image.transferTo(file);
	            BufferedImage img = ImageUtil.change2jpg(file);
	            ImageIO.write(img, "jpg", file);
	        }
	        
	        return "redirect:/admin_category_list";
	              
	 }
}
