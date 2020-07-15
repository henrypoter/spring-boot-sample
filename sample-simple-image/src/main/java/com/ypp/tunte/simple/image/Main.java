package com.ypp.tunte.simple.image;

import com.alibaba.simpleimage.ImageFormat;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.io.*;


/**
 * 功能说明
 *
 * @author yanpp
 * @createDateTime 2020/3/12
 **/
@Slf4j
public class Main {
    protected static ImageFormat outputFormat = ImageFormat.JPEG;
    public static void main(String[] args) {

        //zoom("E:\\temp\\images\\timg.jpg","E:\\temp\\images\\after\\thumbnail.jpg",600,600);
        //rotationAndWatermark("E:\\temp\\images\\timg.jpg","E:\\temp\\images\\after\\image-with-watermark.jpg",600,600,90,"E:\\temp\\images\\timg1.jpg");
        //writeToOutputStream("E:\\temp\\images\\timg6.jpg","E:\\temp\\images\\after\\outputStream.png",1000,1000);
        scaleZoom("E:\\temp\\images\\timg7.jpg","E:\\temp\\images\\after\\scaleZoom25.jpg",0.25f);
        scaleZoom("E:\\temp\\images\\timg7.jpg","E:\\temp\\images\\after\\scaleZoom75.jpg",0.75f);
    }

    /**
     * 固定大小等比缩放
     */
    public final static void zoom(String src,String target,int width,int height){
        try {
            Thumbnails.of(new File(src))
                    .size(width, height)
                    .toFile(new File(target));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 旋转、添加水印
     */
    public final static void rotationAndWatermark(String src,String target,int width,int height,int rotate,String waterImage){
        try {
            Thumbnails.of(new File(src))
                    .size(width, height)
                    .rotate(rotate)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(waterImage)), 0.5f)
                    .outputQuality(0.8)
                    .toFile(new File(target));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入输出流
     * @param src
     * @param target
     * @param width
     * @param height
     */
    public final static void writeToOutputStream(String src,String target,int width,int height){
        OutputStream os = null;
        try {
            os = new FileOutputStream(target);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Thumbnails.of(src)
                    .size(width, height)
                    .outputFormat("png")
                    .toOutputStream(os);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (os!=null) {
                IOUtils.closeQuietly(os);
            }
        }


    }

    /**
     * 等比缩放
     * @param src
     * @param target
     * @param scale
     */
    public final static void scaleZoom(String src,String target,double scale){
        try {
            Thumbnails.of(src)
                    .scale(scale)
            .toFile(target);
        } catch (IOException e) {
            log.error("生成等比缩略图错误:{}",e.getLocalizedMessage());
            e.printStackTrace();
        }
    }


}
