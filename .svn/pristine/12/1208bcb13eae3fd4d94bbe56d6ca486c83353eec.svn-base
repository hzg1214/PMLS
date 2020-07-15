package cn.com.eju.deal.common.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

/**
 * 
* 图片压缩工具
* @author wushuang
* @date 2016年11月14日 下午5:50:07
 */
public class ImageCompressUtil
{  

    private static ImageCompressUtil instance;  
    private ImageCompressUtil (){
    }  
    public static synchronized ImageCompressUtil getInstance() {  
        if (instance == null) {  
            instance = new ImageCompressUtil();  
        }  
        return instance;  
    } 
    
    /**
     * 等比例压缩算法：
     * 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
     * @param srcURL 原图地址
     * @param deskURL 缩略图地址
     * @param scale 压缩比例  一般用 0.5
     * @throws Exception
     */
    public Boolean imageCompressRatio(File srcFile, String deskURL, double scale)
    {
        try
        {
            long start = System.currentTimeMillis();
            if (scale > 1)
            {
                scale = 0.5f;
            }
            Image src = ImageIO.read(srcFile);
            int srcHeight = src.getHeight(null);
            int srcWidth = src.getWidth(null);
            int deskHeight = 0;// 缩略图高
            int deskWidth = 0;// 缩略图宽
            deskHeight = (int)(srcHeight * scale);
            deskWidth = (int)(srcWidth * scale);
            
//            BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.SCALE_SMOOTH);
//            tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); //绘制缩小后的图
//            FileOutputStream deskImage = new FileOutputStream(deskURL); //输出到文件流

            
            BufferedImage tag = new BufferedImage(deskWidth,deskHeight, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, Color.white,null);
            FileOutputStream deskImage = new FileOutputStream(deskURL);
            ImageIO.write(tag, "jpg", deskImage);
            deskImage.close();
            long end = System.currentTimeMillis();
            System.out.println("图片：" + deskURL + "，压缩时间：" + (end - start) + "ms");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }
    
}