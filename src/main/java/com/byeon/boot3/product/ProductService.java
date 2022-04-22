package com.byeon.boot3.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.byeon.boot3.board.BoardFilesVO;
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
		
		if(files != null) {

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

		}
		
		return result;
	}
	
	//delete
	public int setDelete(ProductVO productVO) throws Exception{
		
		List<ProductFilesVO> ar = productMapper.getFileList(productVO);
		
		int result = productMapper.setDelete(productVO);
		
		if(result > 0) {
			for(ProductFilesVO vo: ar) {
				boolean check = fileManager.remove("resources/upload/product/", vo.getFileName());
			}
		}
	
		return result;
	}
	
	

}
