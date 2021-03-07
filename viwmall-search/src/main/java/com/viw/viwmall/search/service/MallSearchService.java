package com.viw.viwmall.search.service;

import com.viw.viwmall.search.vo.SearchParam;
import com.viw.viwmall.search.vo.SearchResult;


/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/3 0:44
 * @description:
 */
public interface MallSearchService {


    /**
     *
     * @param param  检索的所有参数
     * @return 返回检索的结果,里面包含页面需要的所有信息
     */
    SearchResult search(SearchParam param);


}
