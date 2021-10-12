package com.chiwei.craft.code.sftp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.google.common.collect.Lists;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import lombok.extern.slf4j.Slf4j;

/**
 * SFTP操作封装
 * 
 * @author phoenix
 * @date 2020年12月8日
 */
@Slf4j
public class SftpTool {

	/**
	 * 连接sftp服务器
	 *
	 * @return
	 */
	public ChannelSftp connect() {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession("username", "ftphost", 21);
			Session sshSession = jsch.getSession("username", "host", 21);
			log.info("Session created ... UserName=" + "username" + ";host=" + "host" + ";port=" + "port");
			sshSession.setPassword("password");
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			log.info("Session connected ...");
			log.info("Opening Channel ...");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			log.info("登录成功");
		} catch (Exception e) {
			log.error("FTP连接失败，请排查", e);
		}
		return sftp;
	}

	/**
	 * 上传文件
	 *
	 * @param directory  上传的目录
	 * @param uploadFile 要上传的文件
	 * @param sftpConfig
	 */
	public void uploadFile(String directory, String uploadFile) {
		ChannelSftp sftp = connect();
		InputStream inputStream = null;
		try {
			sftp.cd(directory);
			File file = new File(uploadFile);
			inputStream = new FileInputStream(file);
			sftp.put(inputStream, file.getName());
			log.info("文件上传成功 {}", file);
		} catch (Exception e) {
			log.error("上传文件失败", e);
		} finally {
			disConnect(sftp);
			closeStream(inputStream, null);
		}
	}

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param directory
	 * @param fileName
	 * @param cont
	 */
	public void uploadStream(String directory, String fileName, String cont) {
		ChannelSftp sftp = connect();
		InputStream inputStream = null;
		try {
			sftp.cd(directory);
			inputStream = new ByteArrayInputStream(cont.getBytes("UTF-8"));
			sftp.put(inputStream, fileName);
			log.info("文件上传成功 {}", fileName);
		} catch (Exception e) {
			log.error("上传文件失败", e);
		} finally {
			disConnect(sftp);
			closeStream(inputStream, null);
		}
	}

	/**
	 * 下载文件
	 *
	 * @param directory    下载目录
	 * @param downloadFile 下载的文件名
	 * @param saveFile     存在本地的路径
	 * @param sftpConfig
	 */
	public void download(String directory, String downloadFile, String saveFile) {
		OutputStream output = null;
		ChannelSftp sftp = connect();
		try {
			File localDirFile = new File(saveFile);
			// 判断本地目录是否存在，不存在需要新建各级目录
			if (!localDirFile.exists()) {
				localDirFile.mkdirs();
			}
			if (log.isInfoEnabled()) {
				log.info("开始获取远程文件:[{}]---->[{}]", new Object[] { directory, saveFile });
			}
			sftp.cd(directory);
			if (log.isInfoEnabled()) {
				log.info("打开远程文件:[{}]", new Object[] { directory });
			}
			output = new FileOutputStream(new File(saveFile.concat(File.separator).concat(downloadFile)));
			sftp.get(downloadFile, output);
			if (log.isInfoEnabled()) {
				log.info("文件下载成功");
			}
		} catch (Exception e) {
			log.error("文件下载出现异常", e);
		} finally {
			disConnect(sftp);
			closeStream(null, output);
		}
	}

	/**
	 * 下载远程文件夹下的所有文件
	 *
	 * @param remoteFilePath
	 * @param localDirPath
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void getFileDir(String remoteFilePath, String localDirPath) {
		File localDirFile = new File(localDirPath);
		// 判断本地目录是否存在，不存在需要新建各级目录
		if (!localDirFile.exists()) {
			localDirFile.mkdirs();
		}
		if (log.isInfoEnabled()) {
			log.info("sftp文件服务器文件夹[{}],下载到本地目录[{}]", new Object[] { remoteFilePath, localDirFile });
		}
		ChannelSftp channelSftp = connect();
		try {
			Vector<LsEntry> lsEntries = channelSftp.ls(remoteFilePath);
			if (log.isInfoEnabled()) {
				log.info("远程目录下的文件为[{}]", lsEntries);
			}
			for (LsEntry entry : lsEntries) {
				String fileName = entry.getFilename();
				if (checkFileName(fileName)) {
					continue;
				}
				String remoteFileName = getRemoteFilePath(remoteFilePath, fileName);
				channelSftp.get(remoteFileName, localDirPath);
			}
		} catch (Exception e) {
			log.error("下载整个文件夹文件异常", e);
		} finally {
			disConnect(channelSftp);
		}
	}

	/**
	 * 关闭流
	 * 
	 * @param outputStream
	 */
	private void closeStream(InputStream inputStream, OutputStream outputStream) {
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (Exception e) {
				log.error("关闭输出流异常", e);
			}
		}
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				log.error("关闭输入流异常", e);
			}
		}
	}

	private boolean checkFileName(String fileName) {
		if (".".equals(fileName) || "..".equals(fileName)) {
			return true;
		}
		return false;
	}

	private String getRemoteFilePath(String remoteFilePath, String fileName) {
		if (remoteFilePath.endsWith("/")) {
			return remoteFilePath.concat(fileName);
		} else {
			return remoteFilePath.concat("/").concat(fileName);
		}
	}

	/**
	 * 删除文件
	 *
	 * @param directory  要删除文件所在目录
	 * @param deleteFile 要删除的文件
	 * @param sftp
	 */
	public void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			log.error("删除文件异常", e);
		}
	}

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param path
	 * @param fileName
	 * @return
	 */
	public String readFile(String path, String fileName) {
		BufferedReader bufferedReader = null;
		ChannelSftp sftp = connect();
		StringBuilder cont = new StringBuilder();
		try {
			sftp.cd(path);
			if (log.isInfoEnabled()) {
				log.info("打开远程文件: {}", path + File.separator + fileName);
			}
			InputStream inputStream = sftp.get(fileName);
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			String temp = null;
			while ((temp = bufferedReader.readLine()) != null) {
				// ....数据处理
				cont.append(temp);
			}
		} catch (Exception e) {
			log.error("文件下载出现异常", e);
		} finally {
			disConnect(sftp);
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (Exception e) {
					log.error("读文件内容 bufferedReader 关闭异常", e);
				}
			}
		}
		return cont.toString();
	}

	/**
	 * 列出目录下的文件
	 *
	 * @param directory  要列出的目录
	 * @param sftpConfig
	 * @return
	 * @throws SftpException
	 */
	@SuppressWarnings("unchecked")
	public List<String> listFiles(String directory) {
		ChannelSftp sftp = connect();
		List<String> fileNameList = Lists.newArrayList();
		try {
			sftp.cd(directory);
			Vector<LsEntry> vector = sftp.ls(directory);
			for (int i = 0; i < vector.size(); i++) {
				LsEntry lsEntry = vector.get(i);
				String fileName = lsEntry.getFilename();
				if (".".equals(fileName) || "..".equals(fileName)) {
					continue;
				}
				fileNameList.add(fileName);
			}
		} catch (Exception e) {
			log.error("列表文件异常", e);
		} finally {
			disConnect(sftp);
		}
		log.info("列出文件列表 {}", fileNameList);
		return fileNameList;
	}

	/**
	 * 断掉连接
	 */
	public void disConnect(ChannelSftp sftp) {
		try {
			sftp.disconnect();
			sftp.getSession().disconnect();
		} catch (Exception e) {
			log.error("断开连接异常", e);
		}
	}

}
