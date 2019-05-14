package cafe.jjdev.mall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.mall.mapper.ProductCommonMapper;
import cafe.jjdev.mall.vo.ProductCommon;

@Service
@Transactional
public class ProductCommonService {
	@Autowired ProductCommonMapper productCommonMapper;
	
	public Map<String, Object> getProductCommonListByCategoryNo (int categoryNo, int currentPage, String searchWord){
		// select 메소드의 매개변수에 startRow와 rowPerPage 그리고 categoryNo를 보낼 것인데 갯수가 많으니 Map으로 보낼 것이다.
		Map<String, Object> map = new HashMap<String, Object>();
		// 한 페이지에서 보여지는 행의 개수
		int rowPerPage = 10;
		// 전체 리스트에서 몇번째 행부터 보여줄 것인지를 계산하는 변수
		int startRow = (currentPage-1)*rowPerPage;
		map.put("categoryNo", categoryNo);
		map.put("startRow", startRow);
		map.put("rowPerPage", rowPerPage);
		map.put("searchWord", "&"+searchWord+"&");
		List<ProductCommon> productCommonList = productCommonMapper.selectProductCommonList(map);
		// 마지막 페이지를 계산하는 변수
		int totalRow = productCommonMapper.selectCount();
		int lastPage = 0;
		if(totalRow/rowPerPage == 0) {
			lastPage = totalRow/rowPerPage;
		}else {
			lastPage = totalRow/rowPerPage+1;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("productCommonList", productCommonList);
		resultMap.put("lastPage", lastPage);
		return resultMap;
	}
	
	// 상품 등록
	public void addProduct(ProductCommon productCommon) {
		productCommonMapper.insertProduct(productCommon);
	}
}
