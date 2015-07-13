import java.io.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.*;



public class ftpTool {
	 public static void testUpload(File uploadFile) { 
	        FTPClient ftpClient = new FTPClient(); 
	        FileInputStream fis = null; 

	        try { 
	            ftpClient.connect("SERVER ADDRESS"); 
	            ftpClient.login("USERID", "PASSWORD"); 
; 
	            fis = new FileInputStream(uploadFile); 
	            //设置上传目录 
	            ftpClient.changeWorkingDirectory("/admin"); 
	            ftpClient.setBufferSize(1024); 
	            ftpClient.setControlEncoding("GBK"); 
	            //设置文件类型（二进制） 
	            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
	            ftpClient.storeFile("BTCC.data", fis); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	            throw new RuntimeException("FTP客户端出错！", e); 
	        } finally { 
	            IOUtils.closeQuietly(fis); 
	            try { 
	                ftpClient.disconnect(); 
	            } catch (IOException e) { 
	                e.printStackTrace(); 
	                throw new RuntimeException("关闭FTP连接发生异常！", e); 
	            } 
	        } 
	    } 
}
