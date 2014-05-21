package com.PPU.DB.security;

import java.security.MessageDigest;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 19.04.14
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
public class MD5 {

      public static String getMD5(String value)
      {
          MessageDigest md5 ;
          StringBuffer  hexString = new StringBuffer();

          try {

              md5 = MessageDigest.getInstance("md5");

              md5.reset();
              md5.update(value.getBytes());

              byte messageDigest[] = md5.digest();

              for (int i = 0; i < messageDigest.length; i++) {
                  hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
              }

              return hexString.toString();
          }
          catch (Exception e) {
              return "";
          }
      }
}
