package com.wei.ai.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class Base64Util {
    private static Logger logger = LoggerFactory.getLogger(Base64Util.class);

    public Base64Util() {
    }

    public static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }

    public static byte[] decode(byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }

    public static String imageToBase64(String imagePath) {
        InputStream in = null;
        byte[] data = null;

        try {
            in = new FileInputStream(imagePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException var4) {
            logger.error("将图片进行Base64位编码失败" + var4.toString());
        }

        return encode(data);
    }

    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null) {
            return false;
        } else {
            try {
                byte[] b = decode(imgStr.getBytes());

                for(int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {
                        b[i] = (byte)(b[i] + 256);
                    }
                }

                OutputStream out = new FileOutputStream(path);
                out.write(b);
                out.flush();
                out.close();
                return true;
            } catch (Exception var4) {
                logger.error("base64字符串转化图片失败" + var4.toString());
                return false;
            }
        }
    }
}
