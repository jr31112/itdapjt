package com.ssafy.itda.itda_test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.itda.itda_test.model.Files;

@Repository
public class FileDao {
	String ns = "ssafy.file.";

	@Autowired
	private SqlSession sqlSession;

	public void insertFile(Files f) {
		sqlSession.insert(ns + "insertFile", f);
	}

	public void deleteFile(int fid) {
		sqlSession.delete(ns + "deleteFile", fid);
	}

	public List<Files> getFiles(int stid) {
		return sqlSession.selectList(ns + "getFiles", stid);
	}

	public Files getFile(int fid) {
		return sqlSession.selectOne(ns + "getFile", fid);
	}

}
