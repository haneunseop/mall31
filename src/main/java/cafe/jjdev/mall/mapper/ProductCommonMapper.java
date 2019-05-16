package cafe.jjdev.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.Product;
import cafe.jjdev.mall.vo.ProductCommon;
@Mapper
public interface ProductCommonMapper {
	public ProductCommon selectProductCommonByProductCommonNO(int productCommonNo);
	public List<ProductCommon> selectProductCommonListByCategoryNo(Map<String, Object> map);
	public int selectCount();
	public void insertProduct(ProductCommon productCommon);
	// 색상 값에 해당하는 size와 stock을 찾는 메소드
	// 생각해보니 동적쿼리를 사용하면 되는거였는데 괜히 만들었다.
	// 수정할 예정
	public List<Product> selectProductByProductColor(String productColor);
}
