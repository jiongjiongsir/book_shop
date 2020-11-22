package neu.edu.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    public static <T>  T CopyCopyParamToBean(Map map, T Bean) throws InvocationTargetException, IllegalAccessException {
        BeanUtils.populate(Bean,map);
        return Bean;
    }
    /**
     * 将字符串转换成为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            if(strInt==null)
            {
                return defaultValue;
            }
            else
            {
                return Integer.parseInt(strInt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
