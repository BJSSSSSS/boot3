package com.byeon.boot3.product;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.byeon.boot3.util.FileManager;
import com.byeon.boot3.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)//각각의 서비스마다 하나씩 넣어줄것!
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private FileManager fileManager;
	
	//fileDelete
	public int setFileDelete(ProductFilesVO productFilesVO) throws Exception{

		
		//DB에서 조회
		productFilesVO = productMapper.getFileDetail(productFilesVO);
						
		//HDD 삭제		//여기서 remove 강사랑 path, fileName 위치 다름 //나중에는 경로명을 properties에 등록해놓고 갖고와도됨
		//boolean result = fileManager.remove("/resources/upload/product", productFilesVO.getFileName());
		//이 경우에 파일을 지울때 실패가 뜰수도 있음, 근데 그 이후에 DB가 실행되면 문제
		//HDD 삭제는 Transaction에 해당하지 않음, 오류 잡기!!
		
		//HDD 삭제가 되면 DB 삭제
//		if(result) {
//			productMapper.setFileDelete(productFilesVO);
//		}
		
		//DB 삭제가 되면 HDD 삭제(이게 더 낫다고 생각)
		int check = productMapper.setFileDelete(productFilesVO);
		if(check > 0) {
			boolean result = fileManager.remove("/resources/upload/product", productFilesVO.getFileName());
		}
		return check;
	}
	

	
	//update
	public int setUpdate(ProductVO productVO, MultipartFile [] files) throws Exception{
	
		int result = productMapper.setUpdate(productVO);
		
		if(files != null) {
			for(MultipartFile f : files) {
				
				if(f.isEmpty()) {
					continue;
				}
				
				String fileName = fileManager.fileSave(f, "/resources/upload/product/");
				
				ProductFilesVO productFilesVO = new ProductFilesVO();
				productFilesVO.setProductNum(productVO.getProductNum());
				productFilesVO.setFileName(fileName);
				productFilesVO.setOriName(f.getOriginalFilename());
				result = productMapper.setFileAdd(productFilesVO);
			}
		}
		return result;
	}
	

	//detail
	public ProductVO getDetail(ProductVO productVO) throws Exception{
		return productMapper.getDetail(productVO);
	}
	
	
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
				
				String fileName = fileManager.fileSave(f, "/resources/upload/product/");
				
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
				boolean check = fileManager.remove("/resources/upload/product/", vo.getFileName());
			}
		}
	
		
		return result;
	}
	
	

}
