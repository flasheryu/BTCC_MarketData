import java.io.*;
import java.awt.event.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class getInfo implements ActionListener{
	private Timer timer;
	
	public getInfo(){
		timer=new Timer(1000,this);
	}
	
	public void start(){
		timer.start();
	}
	
	public String loadJson (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine+'\n');
            }
            in.close();
        } catch (MalformedURLException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return json.toString();
    }
	
	public void actionPerformed(ActionEvent e){
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
		String sysTime=df.format(new Date());
		System.out.println(sysTime);
		timer.stop();
	    String tradeUrl = "https://data.btcchina.com/data/trades";
	    String orderUrl = "https://data.btcchina.com/data/orderbook";
	    String tradeString = loadJson(tradeUrl);
	    String orderString = loadJson(orderUrl);
	    FileOperation.contentToTxt("trade.txt", tradeString);
	    FileOperation.contentToTxt("orderbook.txt", orderString);
	    if (sysTime.equals("00:00:00")){
	    	ftpTool.testUpload(new File("trade.txt"));
	    	ftpTool.testUpload(new File("orderbook.txt"));
	    }
	    timer.restart();
	}
}