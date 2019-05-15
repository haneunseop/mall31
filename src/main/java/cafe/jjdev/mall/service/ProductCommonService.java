package cafe.jjdev.mall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.mall.mapper.ProductCommonMapper;
import cafe.jjdev.mall.vo.Product;
import cafe.jjdev.mall.vo.ProductCommon;

@Service
@Transactional
public class ProductCommonService {
	@Autowired ProductCommonMapper productCommonMapper;
	
	public Map<String, Object> getProductCommonListByCategoryNo (int categoryNo, int currentPage, String searchWord){
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonListByCategoryNo] 서비스 실행 확인");
		// select 메소드의 매개변수에 startRow와 rowPerPage 그리고 categoryNo를 보낼 것인데 갯수가 많으니 Map으로 보낼 것이다.
		Map<String, Object> map = new HashMap<String, Object>();
		// 한 페이지에서 보여지는 행의 개수
		int rowPerPage = 10;
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonListByCategoryNo] rowPerPage: "+rowPerPage);
		// 전체 리스트에서 몇번째 행부터 보여줄 것인지를 계산하는 변수
		int startRow = (currentPage-1)*rowPerPage;
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonListByCategoryNo] startRow: "+startRow);
		map.put("categoryNo", categoryNo);
		map.put("startRow", startRow);
		map.put("rowPerPage", rowPerPage);
		map.put("searchWord", "%"+searchWord+"%");
		List<ProductCommon> productCommonList = productCommonMapper.selectProductCommonListByCategoryNo(map);
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonByProductCommonNO] selectProductCommonList(map) 매퍼 실행 확인");
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonListByCategoryNo] productCommonList: "+productCommonList);

		// 마지막 페이지를 계산하는 변수
		int totalRow = productCommonMapper.selectCount();
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonByProductCommonNO] productCommonMapper.selectCount() 매퍼 실행 확인");
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonListByCategoryNo] totalRow: "+totalRow);
		int lastPage = 0;
		if(totalRow/rowPerPage == 0) {
			lastPage = totalRow/rowPerPage;
		}else {
			lastPage = totalRow/rowPerPage+1;
		}
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonListByCategoryNo] lastPage: "+lastPage);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("productCommonList", productCommonList);
		resultMap.put("lastPage", lastPage);
		return resultMap;
	}
	
	// 상품 등록
	public void addProduct(ProductCommon productCommon) {
		productCommonMapper.insertProduct(productCommon);
	}
	
	// 상품 상세보기
	public ProductCommon getProductCommonByProductCommonNO(int productCommonNo) {
		// productCommonNo값에 해당하는 레코드(행)를 조회
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonByProductCommonNO] 서비스 실행 확인");
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonByProductCommonNO] productCommonNo: "+productCommonNo);
		ProductCommon productCommon = productCommonMapper.selectProductCommonByProductCommonNO(productCommonNo);
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonByProductCommonNO] selectProductCommonByProductCommonNO(productCommonNo) 매퍼 실행 확인");
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonByProductCommonNO] productCommon: "+productCommon);
		return productCommon;
	}
	
	// 상품 주문하기
	public Map<String, Object> getProductCommonByProductCommonNoAndgetProductByProductColor(int productCommonNo, String productColor){
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonByProductCommonNoAndgetProductByProductColor] 서비스 실행 확인");
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonByProductCommonNoAndgetProductByProductColor] productCommonNo: "+productCommonNo);
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonByProductCommonNoAndgetProductByProductColor] productColor: "+productColor);
		
		ProductCommon productCommon = productCommonMapper.selectProductCommonByProductCommonNO(productCommonNo);
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonByProductCommonNoAndgetProductByProductColor] selectProductCommonByProductCommonNO(productCommonNo) 매퍼 실행 확인");
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonByProductCommonNoAndgetProductByProductColor] productCommon: "+productCommon);
		
		List<Product> productList = productCommonMapper.selectProductByProductColor(productColor);
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonByProductCommonNoAndgetProductByProductColor] selectProductByProductColor(productColor) 매퍼 실행 확인");
		System.out.println("[cafe.jjdev.mall.service.ProductCommonService.getProductCommonByProductCommonNoAndgetProductByProductColor] productList: "+productList);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productCommon", productCommon);
		map.put("productList", productList);
		return map;
	}
}
