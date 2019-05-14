package cafe.jjdev.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.ProductCommon;
@Mapper
public interface ProductCommonMapper {
	public ProductCommon selectProductCommonByCategory(int productCommonNo);
	public List<ProductCommon> selectProductCommonList(Map<String, Object> map);
	public int selectCount();
	public void insertProduct(ProductCommon productCommon);
}
