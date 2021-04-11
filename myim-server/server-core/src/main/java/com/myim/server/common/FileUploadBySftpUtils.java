package com.myim.server.common;

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

/**
 * @author cj
 */
public class FileUploadBySftpUtils  {
    private static ChannelSftp sftp;

    private Session session;
    /** FTP 登录用户名*/
    private String username;
    /** FTP 登录密码*/
    private String password;
    /** 私钥 */
    private String privateKey;
    /** FTP 服务器地址IP地址*/
    private String host;
    /** FTP 端口*/
    private int port;

    static {
        FileUploadBySftpUtils sftp = new FileUploadBySftpUtils("root", "617890cJ!pc", "47.110.41.97", 22);
        sftp.login();
    }

    public FileUploadBySftpUtils(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public FileUploadBySftpUtils(String username, String host, int port, String privateKey) {
        this.username = username;
        this.host = host;
        this.port = port;
        this.privateKey = privateKey;
    }

    public FileUploadBySftpUtils(){}

    public void login(){
        try {
            JSch jsch = new JSch();
            if (privateKey != null) {
                jsch.addIdentity(privateKey);// 设置私钥
            }

            session = jsch.getSession(username, host, port);
            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();

            sftp = (ChannelSftp) channel;
        } catch (JSchException ignored) {
        }
    }

    /**
     * 关闭连接 server
     */
    public void logout(){
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

    public static void upload(String directory, String sftpFileName, InputStream input) throws SftpException{
        try {
            if (sftp.isClosed()) {
                FileUploadBySftpUtils sftp = new FileUploadBySftpUtils("root", "617890cJ!pc", "47.110.41.97", 22);
                sftp.login();
            }

            sftp.cd(directory);
        } catch (SftpException e) {
            sftp.mkdir(directory);
            sftp.cd(directory);
        }
        sftp.put(input, sftpFileName);
    }

    public static void upload(String directory, String uploadFile) throws FileNotFoundException, SftpException{
        File file = new File(uploadFile);
        upload(directory, file.getName(), new FileInputStream(file));
    }

    public static void upload(String directory, String sftpFileName, byte[] byteArr) throws SftpException{
        upload(directory, sftpFileName, new ByteArrayInputStream(byteArr));
    }

    public static void upload(String directory, String sftpFileName, String dataStr, String charsetName) throws UnsupportedEncodingException, SftpException{
        upload(directory, sftpFileName, new ByteArrayInputStream(dataStr.getBytes(charsetName)));
    }

    public void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException{
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        File file = new File(saveFile);
        sftp.get(downloadFile, new FileOutputStream(file));
    }

    public byte[] download(String directory, String downloadFile) throws SftpException, IOException{
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        InputStream is = sftp.get(downloadFile);

        byte[] fileData = IOUtils.toByteArray(is);

        return fileData;
    }

    public void delete(String directory, String deleteFile) throws SftpException{
        sftp.cd(directory);
        sftp.rm(deleteFile);
    }

    public Vector<?> listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }

    public static void main(String[] args) throws SftpException, IOException {
        FileUploadBySftpUtils sftp = new FileUploadBySftpUtils("root", "617890cJ!pc", "47.110.41.97", 22);
        sftp.login();
        //byte[] buff = sftp.download("/opt", "start.sh");
        //System.out.println(Arrays.toString(buff));
        File file = new File("C:\\Users\\cj\\Desktop\\xxx.jpeg");
        InputStream is = new FileInputStream(file);

        sftp.upload("/data/first11", "xxx.jpeg", is);
        sftp.logout();
    }
}
