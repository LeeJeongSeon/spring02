package com.example.spring02.controller.upload;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	//로깅
	private static final Logger logger
	=LoggerFactory.getLogger(UploadController.class);
	
	//servlet-context.xml에 선언한 스트링 bean 참조
	@Resource(name = "uploadPath")
	String uploadPath;
	
	//업로드 폼 페이지로 이동
	@RequestMapping(value = "/upload/uploadForm"
			, method = RequestMethod.GET)
	public String uploadForm() {
		return "upload/uploadForm";
	}
	
	//업로드된 내용을 처리
	//MultipartFile : 업로드한 파일이 저장될 수 있도록..하는 객체
	@RequestMapping(value = "/upload/uploadForm"
			, method = RequestMethod.POST)
	public ModelAndView uploadForm(MultipartFile file
			, ModelAndView mav) throws Exception {
		String savedName=file.getOriginalFilename();
		savedName=uploadFile(savedName, file.getBytes());
		mav.setViewName("upload/uploadResult");//뷰의 이름
		mav.addObject("savedName", savedName);//전달할 데이터
		return mav;
	}
	String uploadFile(String originalName, byte[] fileData) 
			throws Exception {
		//UUID(Universal Unique Identifier, 범용고유식별자)
		//중복된파일이 올라갈 경우 서버에서 자동적으로 다른이름을 생성해 업로드시킨다.
		UUID uid=UUID.randomUUID();
		String savedName=uid.toString()+"_"+originalName;
		//File(디렉토리, 파일이름)
		File target=new File(uploadPath, savedName);
		//첨부파일을 임시디렉토리에서 우리가 지정한 디렉토리로 복사
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
	//파일이름 중복 방지 방법
	//1) UUID
	//2) 타임스탬프 : 1970.1.1~현재 밀세컨드
	//3) 테이블에 저장된 파일 이름을 검색해서 수동으로 사용자가 직접입력

}
