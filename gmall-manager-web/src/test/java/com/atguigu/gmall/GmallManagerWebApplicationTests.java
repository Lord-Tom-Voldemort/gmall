package com.atguigu.gmall;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

//spring boot 2.1.x 以下：@RunWith(SpringRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManagerWebApplicationTests {

	@Test
	public void contextLoads() {

	}

	@Test
	public void textFileUpload() throws IOException, MyException {
		String file = this.getClass().getResource("/tracker.conf").getFile();
		ClientGlobal.init(file);
		TrackerClient trackerClient=new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageClient storageClient=new StorageClient(trackerServer,null);
		String orginalFilename="e://01.jpg";
		String[] upload_file = storageClient.upload_file(orginalFilename, "jpg", null);
		for (int i = 0; i < upload_file.length; i++) {
			String s = upload_file[i];
			System.out.println("s = " + s);

//			s = group1
//			s = M00/00/00/wKhQgl4NfkyAdLP-AALGFRpMa2M900.jpg
		}
	}






}
