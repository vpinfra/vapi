package com.vpinfra.platform.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Timestamp;
import java.sql.Types;

/**
 * java 类型转换
 * 
 * @author <a href="mailto:yinjunfeng@vpgame.cn">Yinjf</a>
 * @date 2018年5月30日
 */
public class CustomJavaTypeResolver extends JavaTypeResolverDefaultImpl {

    public CustomJavaTypeResolver() {
        super();
    }

    @Override
    public FullyQualifiedJavaType calculateJavaType(IntrospectedColumn introspectedColumn) {
        FullyQualifiedJavaType type = null;
        switch (introspectedColumn.getJdbcType()) {
            case Types.TIMESTAMP:
                type = new FullyQualifiedJavaType(Timestamp.class.getName());
                break;
            case Types.TINYINT:
                if (introspectedColumn.getLength() == 1) {
                    type = new FullyQualifiedJavaType(Boolean.class.getName());
                } else {
                    type = new FullyQualifiedJavaType(Integer.class.getName());
                }
                break;
            case Types.SMALLINT:
                type = new FullyQualifiedJavaType(Integer.class.getName());
                break;
            case Types.INTEGER:
                type = new FullyQualifiedJavaType(Integer.class.getName());
                break;
            default:
                type = super.calculateJavaType(introspectedColumn);
        }
        return type;
    }
}
