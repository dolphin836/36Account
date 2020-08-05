package dolphin.account.Service;

import dolphin.account.Constant.ApplicationConstant;
import dolphin.account.Constant.ClientConstant;
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
     * @return ClientConstant
     */
    public ClientConstant getClient () {
        String client      = request.getHeader("Client");

        System.out.print(client);

        Integer clientCode = Integer.parseInt(client);

        return ClientConstant.getClient(clientCode);
    }

    /**
     * 获取 Header Application 参数
     * @return ApplicationConstant
     */
    public ApplicationConstant getApplication () {
        String application      = request.getHeader("Application");

        System.out.print(application);

        Integer applicationCode = Integer.parseInt(application);

        return ApplicationConstant.getApplication(applicationCode);
    }
}
