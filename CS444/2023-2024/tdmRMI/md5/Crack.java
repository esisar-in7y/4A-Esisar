package md5;
import java.rmi.Remote;
import java.util.Optional;

public interface Crack extends Remote{

    public Integer cracker(int start, int stop,String to_find,String part) throws Exception;
}
