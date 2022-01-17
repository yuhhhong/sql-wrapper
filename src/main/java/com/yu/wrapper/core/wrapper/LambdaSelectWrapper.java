package com.yu.wrapper.core.wrapper;

import com.yu.wrapper.core.PostfixBuild;
import com.yu.wrapper.core.WhereBuild;
import com.yu.wrapper.core.lambda.LambdaWhereBuild;
import com.yu.wrapper.core.toolkits.Constants;
import com.yu.wrapper.core.toolkits.sqlToolkits.ParamMap;
import com.yu.wrapper.core.toolkits.sqlToolkits.SqlKeyword;
import com.yu.wrapper.core.toolkits.sqlToolkits.SqlSegments;
import com.yu.wrapper.core.toolkits.sqlToolkits.SqlString;

import java.util.Collection;
import java.util.function.Consumer;

public class LambdaSelectWrapper implements LambdaWhereBuild<LambdaSelectWrapper>, PostfixBuild<LambdaSelectWrapper> {
    protected SqlSegments sqlSegments ;

    protected ParamMap paramMap = new ParamMap(Constants.WRAPPER + Constants.DOT + Constants.PARAM_MAP + Constants.DOT);

    @Override
    public LambdaSelectWrapper eq(boolean condition, String column, Object val) {
        if(condition) {
            sqlSegments.add(toSqlString(column), SqlKeyword.EQ, toSqlString(paramMap.putAndGetKey(val)));
        }
        return this;
    }

    @Override
    public LambdaSelectWrapper ne(boolean condition, String column, Object val) {
        if(condition) {
            sqlSegments.add(toSqlString(column), SqlKeyword.NE, toSqlString(paramMap.putAndGetKey(val)));
        }
        return this;
    }

    @Override
    public LambdaSelectWrapper gt(boolean condition, String column, Object val) {
        return null;
    }

    @Override
    public LambdaSelectWrapper ge(boolean condition, String column, Object val) {
        return null;
    }

    @Override
    public LambdaSelectWrapper lt(boolean condition, String column, Object val) {
        return null;
    }

    @Override
    public LambdaSelectWrapper le(boolean condition, String column, Object val) {
        return null;
    }

    @Override
    public LambdaSelectWrapper between(boolean condition, String column, Object val1, Object val2) {
        return null;
    }

    @Override
    public LambdaSelectWrapper notBetween(boolean condition, String column, Object val1, Object val2) {
        return null;
    }

    @Override
    public LambdaSelectWrapper like(boolean condition, String column, Object val) {
        return null;
    }

    @Override
    public LambdaSelectWrapper notLike(boolean condition, String column, Object val) {
        return null;
    }

    @Override
    public LambdaSelectWrapper likeLeft(boolean condition, String column, Object val) {
        return null;
    }

    @Override
    public LambdaSelectWrapper likeStringight(boolean condition, String column, Object val) {
        return null;
    }

    @Override
    public LambdaSelectWrapper isNull(boolean condition, String column) {
        return null;
    }

    @Override
    public LambdaSelectWrapper isNotNull(boolean condition, String column) {
        return null;
    }

    @Override
    public LambdaSelectWrapper in(boolean condition, String column, Collection<?> coll) {
        return null;
    }

    @Override
    public LambdaSelectWrapper in(boolean condition, String column, Object... values) {
        return null;
    }

    @Override
    public LambdaSelectWrapper notIn(boolean condition, String column, Collection<?> coll) {
        return null;
    }

    @Override
    public LambdaSelectWrapper notIn(boolean condition, String column, Object... values) {
        return null;
    }

    @Override
    public LambdaSelectWrapper and(boolean condition, Consumer<WhereBuild> consumer) {
        return null;
    }

    @Override
    public LambdaSelectWrapper or(boolean condition, Consumer<WhereBuild> consumer) {
        return null;
    }

    @Override
    public LambdaSelectWrapper or(boolean condition) {
        return null;
    }

    @Override
    public LambdaSelectWrapper nested(boolean condition, Consumer<WhereBuild> consumer) {
        return null;
    }

    @Override
    public LambdaSelectWrapper not(boolean condition, Consumer<WhereBuild> consumer) {
        return null;
    }

    @Override
    public LambdaSelectWrapper applySql(boolean condition, String applySql, Object... values) {
        return null;
    }

    @Override
    public LambdaSelectWrapper groupBy(boolean condition, String column) {
        return null;
    }

    @Override
    public LambdaSelectWrapper groupBy(boolean condition, String... columns) {
        return null;
    }

    @Override
    public LambdaSelectWrapper orderBy(boolean condition, boolean isAsc, String column) {
        return null;
    }

    @Override
    public LambdaSelectWrapper orderBy(boolean condition, boolean isAsc, String... columns) {
        return null;
    }

    @Override
    public LambdaSelectWrapper having(boolean condition, String sqlHaving, Object... params) {
        return null;
    }

    protected SqlString toSqlString(String s) {
        return () -> s;
    }
}
