package cafe.jjdev.mall.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.service.ProductCommonService;
import cafe.jjdev.mall.vo.Product;
import cafe.jjdev.mall.vo.ProductCommon;

@Controller
public class ProductCommonController {
	@Autowired ProductCommonService productCommonService;
	
	// 상품 리스트 조회
	@GetMapping("/product/getProductListByCategory")
	public String getProductListByCategory(Model model, @RequestParam(value ="categoryNo") int categoryNo
			, @RequestParam(value = "currentPage", required=false, defaultValue = "1") int currentPage
			, @RequestParam(value = "searchWord", defaultValue = "") String searchWord) {
		System.out.println("[cafe.jjdev.mall.controller.ProductCommonController.getProductListByCategory] GET 컨트롤러 실행 확인");
		System.out.println("[cafe.jjdev.mall.controller.ProductCommonController.getProductListByCategory] categoryNo: "+categoryNo);
		System.out.println("[cafe.jjdev.mall.controller.ProductCommonController.getProductListByCategory] currentPage: "+currentPage);
		System.out.println("[cafe.jjdev.mall.controller.ProductCommonController.getProductListByCategory] searchWord: "+searchWord);
		Map<String, Object> map = productCommonService.getProductCommonListByCategoryNo(categoryNo, currentPage, searchWord);
		System.out.println("[cafe.jjdev.mall.controller.ProductCommonController.getProductListByCategory] getProductCommonListByCategoryNo(categoryNo, currentPage, searchWord) 서비스 리턴 확인");
		model.addAttribute("productCommonList", map.get("productCommonList"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("categoryNo", categoryNo);
		model.addAttribute("lastPage", map.get("lastPage"));
		
		return "product/getProductListByCategory";
	}
	
	// 상품 등록 양식으로 이동
	@GetMapping("/product/addProduct")
	public String addProduct() {
		System.out.println("[cafe.jjdev.mall.controller.ProductCommonController.addProduct] GET 컨트롤러 실행 확인");
		return "product/addProduct";
	}
	
	// 상품을 등록한다.
	@PostMapping("/product/addProduct")
	public String addProduct(ProductCommon productCommon) {
		productCommonService.addProduct(productCommon);
		return "redirect:/index";
	}
	
	// 상품 주문하기 폼으로 이동
	@GetMapping("/product/orderProduct")
	public String getProductCommon(Model model, @RequestParam(value="productCommonNo") int productCommonNo) {
		System.out.println("[cafe.jjdev.mall.controller.ProductCommonController.getProductCommon] GET 컨트롤러 실행 확인");
		System.out.println("[cafe.jjdev.mall.controller.ProductCommonController.getProductCommon] productCommonNo: "+productCommonNo);
		ProductCommon productCommon = productCommonService.getProductCommonByProductCommonNO(productCommonNo);
		System.out.println("[cafe.jjdev.mall.controller.ProductCommonController.getProductCommon] getProductCommonByProductCommonNO(productCommonNo) 서비스 리턴 확인");
		model.addAttribute("productCommon", productCommon);
		
		return "product/orderProduct";
	}
	
	@PostMapping("/product/orderProduct")
	public String orderProduct(Model model, @RequestParam(value="productColor") String productColor, @RequestParam(value="productCommonNo") int productCommonNo) {
		System.out.println("[cafe.jjdev.mall.controller.ProductCommonController.orderProduct] POST 컨트롤러 실행 확인");
		System.out.println("[cafe.jjdev.mall.controller.ProductCommonController.orderProduct] productColor: "+productColor);
		System.out.println("[cafe.jjdev.mall.controller.ProductCommonController.orderProduct] productCommonNo: "+productCommonNo);
		Map<String, Object> map = productCommonService.getProductCommonByProductCommonNoAndgetProductByProductColor(productCommonNo, productColor);
		System.out.println("[cafe.jjdev.mall.controller.ProductCommonController.orderProduct] getProductCommonByProductCommonNoAndgetProductByProductColor(productCommonNo, productColor) 서비스 리턴 확인");
		model.addAttribute("productCommon", map.get("productCommon"));
		model.addAttribute("productList", map.get("productList"));
		return "product/orderProduct";
	}
}
