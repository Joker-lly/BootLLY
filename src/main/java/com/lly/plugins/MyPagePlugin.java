/*
 * 分页插件
 */
package com.lly.plugins;

import com.lly.util.page.PageUtil;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;


/**
 * 功能描述：自己编写的mybatis的分页插件
 * args : 你需要mybatis传入什么参数给你 type :你需要拦截的对象  method=要拦截的方法
 * @since 2020-03-29
 * @author Joker-lly
 */
@Intercepts(@Signature(type = StatementHandler.class,method ="prepare",args = {Connection.class,Integer.class}))
public class MyPagePlugin implements Interceptor {
    // 数据库类型
    String databaseType = "";

    // 拦截的方法名后缀匹配规则
    String pageSqlId = "";
    public String getDatabaseType() {return databaseType;}
    public void setDatabaseType(String databaseType) {this.databaseType = databaseType; }
    public String getPageSqlId() {return pageSqlId;}
    public void setPageSqlId(String pageSqlId) {this.pageSqlId = pageSqlId;}

    //我们自己拦截器里面的逻辑
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //第一步获得statmentHaderler ,强转
        StatementHandler statementHandler =(StatementHandler) invocation.getTarget();
        //第二步，获得mateObject对象

        MetaObject metaObject = MetaObject.forObject(statementHandler,SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,new DefaultReflectorFactory());
        String sqlid = (String)metaObject.getValue("delegate.mappedStatement.id");

        // 判断 方法名后缀
        if(sqlid.matches(pageSqlId)){
            // 获得parameterHandler 参数处理器？
            ParameterHandler parameterHandler = statementHandler.getParameterHandler();

            // 获得将要执行的sql
            String sql = statementHandler.getBoundSql().getSql();

            // 拿到数据库连接对象
            Connection connection = (Connection)invocation.getArgs()[0];
            String countSql = new StringBuilder("select count(0) from ").append("( ")
                    .append(sql).append(" )").toString();
            // 渲染参数
            PreparedStatement preparedStatement = connection.prepareStatement(countSql);
            // 交个mybatis处理
            parameterHandler.setParameters(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
            // 关闭 资源
            resultSet.close();
            preparedStatement.close();
            Map<String,Object> parameterObject = (Map<String ,Object>)parameterHandler.getParameterObject();

            // 有漏洞 ，有限制，外部必须在map中传入的 pageUtil 的key 必须为 page，有可能跟业务参数冲突
            PageUtil pageUtil = (PageUtil) parameterObject.get("page");
            pageUtil.setCount(count);

            // 拼接分页sql；
            String pageSql = getPageSql(sql,pageUtil);
            // 将拼接好的sql继续教给 mybatis
            metaObject.setValue("delegate.boundSql.sql",pageSql);
        }
        //推进拦截器调用链
        return invocation.proceed();
    }

    /**
     *  描述： 拼接分页sql
     *
     * @param sql 需要被拼接的sql
     * @param pageUtil
     * @return 拼接好的sql
     */
    public String getPageSql(String sql,PageUtil pageUtil){
        if(databaseType.equals("mysql")){
            return sql+" limit "+pageUtil.getStart()+","+pageUtil.getLimit();
        }else if(databaseType.equals("oracle")){
            //拼接oracle的分语句
        }
        return sql+" limit "+pageUtil.getStart()+","+pageUtil.getLimit();
    }

    /**
     * 描述：需要你返回一个动态代理后的对象  target :StatementHandler
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    /**
     * 描述：会传入配置文件内容 用户可根据配置文件自定义
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {}
}
