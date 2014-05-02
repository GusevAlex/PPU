import com.PPU.DB.security.MD5;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 02.05.14
 * Time: 16:05
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        String s = MD5.getMD5("mypassword2103");
        s = MD5.getMD5("1234");
    }
}
