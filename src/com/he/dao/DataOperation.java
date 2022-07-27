package com.he.dao;

import java.util.List;

/**
 * @author 13253
 * @date 2021/7/29 18:59
 * @className DataOperation
 */
public interface DataOperation<c> {
    /**
     * 得到数据
     *
     * @return 返回需要的数据
     */
    List<c> findData(c clazz, int currentPage, int pageIndex);
}
