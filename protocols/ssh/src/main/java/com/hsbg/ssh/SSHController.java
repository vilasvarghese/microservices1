package com.hsbg.ssh;

import java.io.ByteArrayOutputStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@RestController
public class SSHController {
	
	@RequestMapping("/ssh")
	public String executeSSH() throws Exception {
		return listFolderStructure("vagrant", "vagrant", "192.168.253.37", 22, "ls -ltr");
	}
	
	public static String listFolderStructure(String username, String password, 
			String host, int port, String command) throws Exception {

		Session session = null;
		ChannelExec channel = null;

		try {
			session = new JSch().getSession(username, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();

			channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand(command);
			ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
			channel.setOutputStream(responseStream);
			channel.connect();

			while (channel.isConnected()) {
				Thread.sleep(100);
			}

			String responseString = new String(responseStream.toByteArray());
			System.out.println(responseString);
			return responseString;
		} finally {
			if (session != null) {
				session.disconnect();
			}
			if (channel != null) {
				channel.disconnect();
			}
		}
	}
}
