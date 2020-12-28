import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.zzy.annocation.Autowired;
import com.zzy.annocation.Service;
import com.zzy.annocation.Transactional;
import com.zzy.factory.BeanFactory;
import com.zzy.factory.ProxyFactory;
import com.zzy.service.TransferService;
import com.zzy.service.TransferServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.PublicKey;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangyue666
 * @description
 * @date 2020/12/27 19:37
 */
public class testReflections {

    @Test
    public void test() throws SQLException {
        //设置全局数据源
        try {
            DruidDataSource druidDataSource = new DruidDataSource();
            //数据库连接初始化
            druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            //druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            // druidDataSource.setDbType("com.alibaba.druid.pool.DruidDataSource");
            druidDataSource.setUrl("jdbc:mysql://192.168.249.157:3306/mybatis?useUnicode=true&characterEncoding=utf8&useSSL=true");
            druidDataSource.setUsername("root");
            druidDataSource.setPassword("123456");

            DruidPooledConnection connection = druidDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
