package com.ssafy.itda.itda_test.service;

import java.util.List;

import com.ssafy.itda.itda_test.model.Files;

public interface IFileService {
	void insertFile(Files f);
	void deleteFile(int fid);
	List<Files> getFiles(int stid);
	Files getFile(int fid);
}	
