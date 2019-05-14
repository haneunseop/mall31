package cafe.jjdev.mall.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.service.ProductCommonService;
import cafe.jjdev.mall.vo.ProductCommon;

@Controller
public class ProductCommonController {
	@Autowired ProductCommonService productCommonService;
	
	@GetMapping("/product/getProductListByCategory")
	public String getProductListByCategory(Model model, @RequestParam(value ="categoryNo") int categoryNo
			, @RequestParam(value = "currentPage", required=false, defaultValue = "1") int currentPage
			, @RequestParam(value = "searchWord", defaultValue = "") String searchWord) {
		System.out.println("-----------------categoryNo-----------: "+categoryNo);
		System.out.println("-----------------currentPage-----------: "+currentPage);
		System.out.println("-----------------searchWord-----------: "+searchWord);
		Map<String, Object> map = productCommonService.getProductCommonListByCategoryNo(categoryNo, currentPage, searchWord);
		model.addAttribute("productCommonList", map.get("productCommonList"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("categoryNo", categoryNo);
		model.addAttribute("lastPage", map.get("lastPage"));
		
		return "/product/getProductListByCategory";
	}
	
	@GetMapping("/product/addProduct")
	public String addProduct() {
		// 상품 등록 form으로 이동
		return "/product/addProduct";
	}
	
	@PostMapping("/product/addProduct")
	public String addProduct(ProductCommon productCommon) {
		productCommonService.addProduct(productCommon);
		return "redirect:/index";
	}
}
