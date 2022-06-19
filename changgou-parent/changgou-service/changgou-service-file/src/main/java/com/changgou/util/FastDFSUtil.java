package com.changgou.util;


import com.changgou.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//文件操作的工具类  文件的上传 下载 删除 文件信息额获取 Storage信息的获取 Tracker信息获取
public class FastDFSUtil {

    /**|
     *加载Tracker连接信息
     */
    static {
        String filename = new ClassPathResource("fdfs.client.conf").getPath();
        try {
            ClientGlobal.init(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 文件上传
     */
    public static String[] uplocad(FastDFSFile fastDFSFile) throws Exception {
        //创建一个Tracker访问的客户端对象TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient访问TrackerServer服务 获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer的链接信息可以获取Storage的链接信息，创建StorageClient对象，存储Storage的链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //通过StorageClient访问Storage，实现文件的上传，并获取文件上传后的存储信息
        String[] uploads = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), null);
        //参数的含义 1、上传文件的字节数组 2、文件的扩展名 3、附加参数 比如拍摄地点
        /**
         *
         uploads 有两个参数
         uploads[0] 文件上传所存储的Stroage的组的名字 group1
         uploads[1] 文件存储到Storage上的文件的名字 M00/02/44
         */
        return uploads;
    }

    /**
     * 获取文件信息
     *
     * @param groupName:               文件的组名
     * @param remoteFileName:文件存储路径的名字
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws Exception {
        //先创建一个trackerClient对象 通过这个对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer链接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer获取storage信息 创建StorageClient对象存储Storage信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        // 获取文件信息
        FileInfo file_info = storageClient.get_file_info(groupName, remoteFileName);
        return file_info;
    }

    /**
     * 文件的下载
     */
    public static InputStream downLoadFile(String groupName, String remoteFileName) throws Exception {
        //先创建一个trackerClient对象 通过这个对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer链接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer获取storage信息 创建StorageClient对象存储Storage信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //下载文件
        byte[] buffer = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(buffer);
    }

    /**
     * w文件的删除
     *
     * @param
     * @throws
     */
    public static void deleteFile(String groupName, String remoteFileName) throws Exception {
        //先创建一个trackerClient对象 通过这个对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer链接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer获取storage信息 创建StorageClient对象存储Storage信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //删除文件
        storageClient.delete_file(groupName, remoteFileName);
    }

    /**
     * 获取storage的信息
     *
     * @param
     * @throws Exception
     */
    public static StorageServer getStorages() throws Exception {
        //先创建一个trackerClient对象 通过这个对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer链接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorage(trackerServer);
    }

    public static void main(String[] args) throws Exception {
//        //方法下载的模拟
//        InputStream is = downLoadFile("group1", "////");
//
//        //将文件写入本地磁盘
//        FileOutputStream os = new FileOutputStream("D:/1.jpg");
//
//        //定义一个缓冲区
//        byte[] buffer = new byte[1024];
//        while (is.read(buffer) != -1) {
//            os.write(buffer);
//        }
//        os.flush();
//        os.close();
//        is.close();

    }
}
