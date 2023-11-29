package tdm9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile2 {
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	public static String byteArrayToHex(byte[] a) {
		   StringBuilder sb = new StringBuilder(a.length * 2);
		   for(byte b: a)
		      sb.append(String.format("%02x", b));
		   return sb.toString();
	}
    public static void main(String[] args) throws IOException {
    	byte[] from = {
    			0x00,0x00,0x00,0x00,(byte) 0xbf,(byte) 0xf1,0x41,(byte) 0xbf,(byte) 0xbf,(byte) 0xf1,0x41,(byte) 0xbf
		};
    	String res= new String(from);
    	System.out.println(byteArrayToHex(from));
        System.out.println(byteArrayToHex(res.getBytes()));
    }
}
