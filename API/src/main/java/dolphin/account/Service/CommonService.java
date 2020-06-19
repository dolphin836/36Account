package dolphin.account.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dolphin
 */
@Service
public class CommonService {
    @Autowired
    private HttpServletRequest request;

    /**
     * 获取 Header Client 参数
     * @return Byte
     */
    public Byte getClient () {
        String client = request.getHeader("Client");

        return (byte) Integer.parseInt(client);
    }

    /**
     * 获取 Header Application 参数
     * @return Byte
     */
    public Byte getApplication () {
        String application = request.getHeader("Application");

        return (byte) Integer.parseInt(application);
    }
}
