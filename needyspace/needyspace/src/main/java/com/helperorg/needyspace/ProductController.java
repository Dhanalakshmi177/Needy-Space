package com.helperorg.needyspace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.helperorg.needyspace.domain.Product;
import com.helperorg.needyspace.domain.TableView;

@Controller
@RequestMapping("/product")
public class ProductController {

	@RequestMapping(method = RequestMethod.POST,value = "/add")
	public ModelAndView add(String productName,String 
			quantity,String 
			quality,String 
			cityName,String 
			mobileNo,String 
			description,String 
			mailId,@RequestParam("picture") MultipartFile multipartFile) {
		Product product = new Product();
		product.setProductName(productName);
		product.setCityName(cityName);
		product.setDescription(description);
		product.setMailId(mailId);
		product.setMobileNo(mobileNo);
		ModelAndView modelAndView = new ModelAndView();
		try {
			product.setPicture("data:image/png;base64," + Base64.encodeBase64(multipartFile.getBytes()));
			StringBuilder sb = new StringBuilder();
			sb.append("data:image/png;base64,");
			sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(multipartFile.getBytes(), false)));
			product.setPicture(sb.toString());
		} catch (IOException e) {
			modelAndView.addObject("message","Image size long !");
			modelAndView.addObject("isMsgAvailable",true);
			modelAndView.setViewName("helper");
			return modelAndView;
		}
		product.setQuality(quality);
		product.setQuantity(quantity);
			Database.productList.add(product);
			modelAndView.setViewName("helper");
			modelAndView.addObject("message","Product added !");
			modelAndView.addObject("isMsgAvailable",true);
			return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET,value = "/search")
	public ModelAndView search(String productName,String cityName) {
		ModelAndView mv = new ModelAndView();
	    List<String> headersList = Arrays.asList("productName", 
				"quantity", 
				"quality", 
				"cityName", 
				"mobileNo", 
				"description", 
				"picture", 
				"mailId");
	    TableView tableView  = new TableView();
	    tableView.setTableHeaders(headersList);
	    mv.setViewName("needyresult");
		List<Product> products = new ArrayList<>();
		if(org.springframework.util.StringUtils.hasText(productName) || org.springframework.util.StringUtils.hasText(cityName)) {
			Database.productList.stream().forEach(product -> {
				if((org.springframework.util.StringUtils.hasText(productName) && org.springframework.util.StringUtils.hasText(product.getProductName()) && product.getProductName().contains(productName)) ||
						(org.springframework.util.StringUtils.hasText(cityName) && org.springframework.util.StringUtils.hasText(product.getCityName()) && product.getCityName().contains(cityName))){
					products.add(product);
				}
			});
			mv.addObject("isResultNotAvailable",products.isEmpty());
			mv.addObject("products",products);
			return mv;
		}
		mv.addObject("isResultNotAvailable",Database.productList.isEmpty());
		mv.addObject("products",Database.productList);
		return mv;
	}

}
