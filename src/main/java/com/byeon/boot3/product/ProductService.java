package com.byeon.boot3.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.byeon.boot3.util.FileManager;
import com.byeon.boot3.util.Pager;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private FileManager fileManager;
	
	//list
	public List<ProductVO> getList(Pager pager) throws Exception{
		pager.makeRow();
		pager.makeNum(productMapper.total(pager));
		return productMapper.getList(pager);
	}
	
	//add
	public int setAdd(ProductVO productVO, MultipartFile [] files) throws Exception{
		
		int result = productMapper.setAdd(productVO);
		
		for(MultipartFile f : files) {
			
			if(f.isEmpty()) {
				continue;
			}
			
			String fileName = fileManager.fileSave(f, "resources/upload/product/");
			
			ProductFilesVO productFilesVO = new ProductFilesVO();
			productFilesVO.setProductNum(productVO.getProductNum());
			productFilesVO.setFileName(fileName);
			productFilesVO.setOriName(f.getOriginalFilename());
			productMapper.setFileAdd(productFilesVO);
			
		}
	
		return result;
	}
	
	

}
