package net.aimeizi.service;

import net.aimeizi.model.Article;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/21.
 */
public interface ArticleService {

    /**
     * 根据field和queryString全文检索
     * @param field
     * @param queryString
     * @param older
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Map<String,Object> search(String field, String queryString, String older, int pageNumber, int pageSize)  throws Exception;

}
