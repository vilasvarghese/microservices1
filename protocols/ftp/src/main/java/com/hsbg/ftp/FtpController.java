package com.hsbg.ftp;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.github.kuljaninemir.springbootftpclient.FTPFileWriter;

import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class FtpController {


	@Autowired
	FTPFileWriter ftpFileWriter;
	
	@RequestMapping("/ftp")
	public String testFtp() throws Exception{
		try {
			ftpFileWriter.open();
			if(ftpFileWriter.isConnected()){
			    ftpFileWriter.loadFile("/home/vagrant/SundaySchoolInternationalMeetingMinutes.txt", getOutputStream());
			    //ftpFileWriter.saveFile(inputstream, remotepath, false);
			    ftpFileWriter.saveFile("test.txt", "/home/vagrant/test.txt", true);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ftpFileWriter.close();
		}
		return "ftp";
	}
	
	public OutputStream getOutputStream() throws Exception{
		return new FileOutputStream("D:\\testout.txt");
	}
	
	public void close() {
		
	}
}
