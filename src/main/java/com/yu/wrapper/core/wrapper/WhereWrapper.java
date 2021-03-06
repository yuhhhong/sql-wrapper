package com.yu.wrapper.core.wrapper;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.yu.wrapper.core.lambda.LambdaPostfixBuild;
import com.yu.wrapper.core.lambda.LambdaWhereBuild;
import com.yu.wrapper.core.toolkits.Constants;
import com.yu.wrapper.core.toolkits.mybatisToolkits.MybatisKeyword;
import com.yu.wrapper.core.toolkits.sqlToolkits.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public abstract class WhereWrapper<ImplClass extends WhereWrapper<ImplClass>> implements LambdaWhereBuild<ImplClass>, LambdaPostfixBuild<ImplClass>, SqlString {
    protected SqlSegments sqlSegments = new SqlSegments();

    protected ParamMap paramMap;

    /**
     * this，用于返回链式
     */
    protected final ImplClass typedThis = (ImplClass) this;

    /**
     * 返回一个用于嵌套的子对象
     */
    protected abstract ImplClass children();

    public WhereWrapper() {
        this.paramMap = new ParamMap(Constants.DEFAULT_PLACEHOLDER_PREFIX);
    }

    public WhereWrapper(ParamMap paramMap) {
        this.paramMap = paramMap;
    }

    @Override
    public ImplClass eq(boolean condition, String column, Object val) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.EQ, SqlStringFactory.toSqlString(putParamMapAndGetKey(val)));
        }
        return typedThis;
    }

    @Override
    public ImplClass ne(boolean condition, String column, Object val) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.NE, SqlStringFactory.toSqlString(putParamMapAndGetKey(val)));
        }
        return typedThis;
    }

    @Override
    public ImplClass gt(boolean condition, String column, Object val) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.GT, SqlStringFactory.toSqlString(putParamMapAndGetKey(val)));
        }
        return typedThis;
    }

    @Override
    public ImplClass ge(boolean condition, String column, Object val) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.GE, SqlStringFactory.toSqlString(putParamMapAndGetKey(val)));
        }
        return typedThis;
    }

    @Override
    public ImplClass lt(boolean condition, String column, Object val) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.LT, SqlStringFactory.toSqlString(putParamMapAndGetKey(val)));
        }
        return typedThis;
    }

    @Override
    public ImplClass le(boolean condition, String column, Object val) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.BETWEEN, SqlStringFactory.toSqlString(putParamMapAndGetKey(val)));
        }
        return typedThis;
    }

    @Override
    public ImplClass between(boolean condition, String column, Object val1, Object val2) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.BETWEEN,
                    SqlStringFactory.toSqlString(putParamMapAndGetKey(val1)), SqlKeyword.AND, SqlStringFactory.toSqlString(putParamMapAndGetKey(val2)));
        }
        return typedThis;
    }

    @Override
    public ImplClass notBetween(boolean condition, String column, Object val1, Object val2) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.NOT_BETWEEN,
                    SqlStringFactory.toSqlString(putParamMapAndGetKey(val1)), SqlKeyword.AND, SqlStringFactory.toSqlString(putParamMapAndGetKey(val2)));
        }
        return typedThis;
    }

    @Override
    public ImplClass like(boolean condition, String column, Object val) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.LIKE, SqlStringFactory.toSqlString(putParamMapAndGetKey(MybatisKeyword.PERCENT + val + MybatisKeyword.PERCENT)));
        }
        return typedThis;
    }

    @Override
    public ImplClass notLike(boolean condition, String column, Object val) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.NOT_LIKE, SqlStringFactory.toSqlString(putParamMapAndGetKey(MybatisKeyword.PERCENT + val + MybatisKeyword.PERCENT)));
        }
        return typedThis;
    }

    @Override
    public ImplClass likeLeft(boolean condition, String column, Object val) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.LIKE, SqlStringFactory.toSqlString(putParamMapAndGetKey(MybatisKeyword.PERCENT + val)));
        }
        return typedThis;
    }

    @Override
    public ImplClass likeRight(boolean condition, String column, Object val) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.LIKE, SqlStringFactory.toSqlString(putParamMapAndGetKey(val + MybatisKeyword.PERCENT)));
        }
        return typedThis;
    }

    @Override
    public ImplClass isNull(boolean condition, String column) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.IS_NULL);
        }
        return typedThis;
    }

    @Override
    public ImplClass isNotNull(boolean condition, String column) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.IS_NOT_NULL);
        }
        return typedThis;
    }

    @Override
    public ImplClass in(boolean condition, String column, Collection<?> values) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.IN, SqlStringFactory.toSqlString(values, this::putParamMapAndGetKey));
        }
        return typedThis;
    }

    @Override
    public ImplClass in(boolean condition, String column, Object... values) {
        return in(condition, column, Arrays.asList(values));
    }

    @Override
    public ImplClass notIn(boolean condition, String column, Collection<?> values) {
        if(condition) {
            addSqlSegments(SqlStringFactory.toSqlString(column), SqlKeyword.NOT_IN, SqlStringFactory.toSqlString(values, this::putParamMapAndGetKey));
        }
        return typedThis;
    }

    @Override
    public ImplClass notIn(boolean condition, String column, Object... values) {
        return notIn(condition, column, Arrays.asList(values));
    }

    @Override
    public ImplClass and(boolean condition, Consumer<ImplClass> consumer) {
        return and(condition).nested(condition, consumer);
    }

    @Override
    public ImplClass and(boolean condition) {
        if (condition) {
            addSqlSegments(SqlKeyword.AND);
        }
        return typedThis;
    }

    @Override
    public ImplClass or(boolean condition, Consumer<ImplClass> consumer) {
        return or(condition).nested(condition, consumer);
    }

    @Override
    public ImplClass or(boolean condition) {
        if (condition) {
            addSqlSegments(SqlKeyword.OR);
        }
        return typedThis;
    }

    @Override
    public ImplClass nested(boolean condition, Consumer<ImplClass> consumer) {
        if (condition) {
            ImplClass implClass = children();
            consumer.accept(implClass);
            addSqlSegments(SqlKeyword.APPLY, implClass);
        }
        return typedThis;
    }

    @Override
    public ImplClass not(boolean condition, Consumer<ImplClass> consumer) {
        return not(condition).nested(condition, consumer);
    }

    @Override
    public ImplClass not(boolean condition) {
        if (condition) {
            addSqlSegments(SqlKeyword.NOT);
        }
        return typedThis;
    }

    @Override
    public ImplClass apply(boolean condition, String applySql, Object... values) {
        if(condition) {
            addSqlSegments(SqlKeyword.APPLY, SqlStringFactory.toSqlString(formatSqlParam(applySql, null, values)));
        }
        return typedThis;
    }

    @Override
    public ImplClass exists(boolean condition, String existsSql, Object... values) {
        if(condition) {
            addSqlSegments(SqlKeyword.EXISTS, SqlStringFactory.toSqlString(Constants.LEFT_BRACKET + formatSqlParam(existsSql, null, values) + Constants.RIGHT_BRACKET));
        }
        return typedThis;
    }

    @Override
    public ImplClass notExists(boolean condition, String existsSql, Object... values) {
        return not(condition).exists(condition, existsSql, values);
    }

    @Override
    public ImplClass groupBy(boolean condition, List<String> columns) {
        if(condition) {
            addSqlSegments(SqlKeyword.GROUP_BY, SqlStringFactory.toSqlString(columns));
        }
        return typedThis;
    }

    @Override
    public ImplClass orderBy(boolean condition, boolean isAsc, List<String> columns) {
        if(condition) {
            addSqlSegments(SqlKeyword.ORDER_BY, SqlStringFactory.toSqlString(columns));
        }
        return typedThis;
    }

    @Override
    public ImplClass having(boolean condition, String havingSql, Object... params) {
        if(condition) {
            addSqlSegments(SqlKeyword.HAVING, SqlStringFactory.toSqlString(formatSqlParam(havingSql, null, params)));
        }
        return null;
    }

    @Override
    public String getSqlString() {
        return sqlSegments.getSqlString();
    }

    /**
     *  新增sql片段，如果要修改保存sql片段的实现方式可以重写该函数
     */
    protected void addSqlSegments(SqlString... sqlStrings) {
        sqlSegments.add(sqlStrings);
    }

    /**
     *  设置变量值并且返回变量对应占位符
     */
    protected String putParamMapAndGetKey(Object param) {
        return paramMap.putAndGetKey(param);
    }

    /**
     *  设置变量值并且返回变量对应占位符
     */
    protected String putParamMapAndGetKey(Object param, String mapping) {
        return paramMap.putAndGetKey(param, mapping);
    }

    /**
     * 传入参数序号，输出apply等语句需要的参数占位符名称
     */
    public static String getSqlParamTarget(Integer i) {
        return Constants.LEFT_BRACE + i + Constants.RIGHT_BRACE;
    }

    /**
     *  把apply等函数传入的参数格式化进sql中
     */
    protected String formatSqlParam(String sqlStr, String mapping, Object... params) {
        if (StrUtil.isBlank(sqlStr)) {
            return null;
        }
        if (ArrayUtil.isNotEmpty(params)) {
            for (int i = 0; i < params.length; ++i) {
                String target = getSqlParamTarget(i);
                sqlStr = sqlStr.replace(target, putParamMapAndGetKey(params[i], mapping));
            }
        }
        return sqlStr;
    }

    /**
     * 获取构建出来的sql片段
     */
    public String getSqlSegment() {
        String sqlString = sqlSegments.getSqlString();
        if (StrUtil.isNotBlank(sqlString)) {
            return Constants.WHERE + Constants.SPACE + sqlString;
        } else {
            return Constants.EMPTY;
        }
    }
}
