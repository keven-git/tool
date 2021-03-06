package file.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * 
 * @Title: TxtReadWriteUtil.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>txt文件的读写操作工具
 * @Created on 2014-4-4 下午06:50:04
 * @author 杨凯
 */
public class TxtReadAndWrite {

    /**
     * 写txt 方式一
     * 
     * @param conent
     * @param txtPath
     * @param isAppend
     */
    public synchronized static void writerTXT(String conent, String txtPath, boolean isAppend) {
        try {
            File file = new File(txtPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(txtPath, isAppend);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(conent);
            bw.newLine();
            fileWriter.flush();
            bw.close();
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写txt 方式二
     * 
     * @param file
     * @param sb
     */
    public static void createTxt(String file, StringBuffer sb) {
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("gbk");
            format.setExpandEmptyElements(true);
            format.setTrimText(false);
            FileOutputStream fos = new FileOutputStream(file);
            XMLWriter xmlWriter = new XMLWriter(fos, format);
            xmlWriter.write(sb.toString());
            xmlWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读txt
     * 
     * @param filePath
     * @return
     */
    public static List<String> readTxt(String filePath) {
        List<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
