package training.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import training.dao.DaoException;
import training.dao.ProductDao;
import training.entity.Product;

@Controller
public class ProductsController {

	@Autowired
	ProductDao dao;
	/*
	@RequestMapping("/products")
	public String getAllProducts(Model model) throws DaoException {
		List<Product> list = dao.getAllProducts();
		model.addAttribute("products", list);
		System.out.printf("There are %d products\n", list.size());
		return "/WEB-INF/pages/products.jsp";
	}
	*/
	
	@RequestMapping("/products")
	public ModelAndView getAllProducts() throws DaoException {
		ModelAndView mav = new ModelAndView();
		mav.addObject("products", dao.getAllProducts());
		mav.setViewName("products");
		return mav;
	}
}
