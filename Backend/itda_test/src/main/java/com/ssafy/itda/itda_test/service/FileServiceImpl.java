package com.ssafy.itda.itda_test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.itda.itda_test.dao.FileDao;
import com.ssafy.itda.itda_test.model.Files;

@Service
public class FileServiceImpl implements IFileService {

	@Autowired
	private FileDao fileDao;
	
	@Override
	public void insertFile(Files f) {
		fileDao.insertFile(f);
	}

	@Override
	public void deleteFile(int fid) {
		fileDao.deleteFile(fid);
	}

	@Override
	public List<Files> getFiles(int stid) {
		return fileDao.getFiles(stid);
	}

	@Override
	public Files getFile(int fid) {
		return fileDao.getFile(fid);
	}

}
