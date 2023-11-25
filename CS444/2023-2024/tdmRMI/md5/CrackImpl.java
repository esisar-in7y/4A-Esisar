package md5;
import java.util.Optional;
import java.security.MessageDigest;

public class CrackImpl implements Crack
{
    MessageDigest md;
    public Integer cracker(int start, int stop,String to_find_hex,String part) throws Exception
    {
        md = MessageDigest.getInstance("MD5");
        System.out.println("Crack de "+start+" à "+ stop);
        byte[] to_find = new byte[16];
        byte[] guess_hash = new byte[16];
        for(int i = 0; i < 16; i++)
        {
            to_find[i] = (byte)Integer.parseInt(to_find_hex.substring(i*2, i*2+2), 16);
        }
        for(int i = start; i <= stop; i++)
        {
            guess_hash=md.digest((part+String.format("%09d", i)).getBytes());
            // print the byte[] in hex
            for (byte b : guess_hash) {
                System.out.printf("%02X", b);
            }
            System.out.println();
            for (byte b : to_find) {
                System.out.printf("%02X", b);
            }
            if(MessageDigest.isEqual(guess_hash, to_find))
            {
                System.out.println("Trouvé");
                return i;
            }
        }
        return -1;
    }
}