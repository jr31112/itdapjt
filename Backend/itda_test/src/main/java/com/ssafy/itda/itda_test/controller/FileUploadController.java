package com.ssafy.itda.itda_test.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ssafy.itda.itda_test.exception.FileUploadException;
import com.ssafy.itda.itda_test.model.User;
import com.ssafy.itda.itda_test.payload.FileUploadResponse;
import com.ssafy.itda.itda_test.service.FileUploadDownloadService;
import com.ssafy.itda.itda_test.service.IUserService;
import com.ssafy.itda.itda_test.service.JwtServiceImpl;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api")
@RestController
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private FileUploadDownloadService service;

	@Autowired
	private IUserService userService;

	@Autowired
	private JwtServiceImpl jwtService;

	@PostMapping("/uploadFile")
	public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		String fileName = service.storeFile(file);
		String fileDownloadUri = null;
		Map<String, Object> resultMap = new HashMap<>();
		String token = req.getHeader("jwt-auth-token");
		if (token != null && !token.equals("")) {
			resultMap.putAll(jwtService.get(token));
			int uid = (int) resultMap.get("uid");
			User user = userService.getUser(uid);
			if (user.getUimg() != null) {
				File uimg_file = new File(user.getUimg());
				if (uimg_file.exists()) {
					uimg_file.delete();
				}
			}
			fileDownloadUri = ServletUriComponentsBuilder.fromPath("/images/").path(fileName).toUriString();
			user.setUimg(fileDownloadUri);
			userService.imgUpload(user);
			return new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
		} else {
			return new FileUploadResponse(null, null, null, 0L);
		}
	}
}
