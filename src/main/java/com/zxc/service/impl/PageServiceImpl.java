package com.zxc.service.impl;

import com.zxc.model.Page;
import com.zxc.service.PageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    @Override
    public <T> Page<T> subList(int page, List<T> list) {
        Page<T> pageObj = new Page<>();
        pageObj.setPageSize(DEFAULT_PAGE_SIZE);
        
        int totalNum = list != null ? list.size() : 0;
        pageObj.setTotalNum(totalNum);
        
        int totalPage = (totalNum + DEFAULT_PAGE_SIZE - 1) / DEFAULT_PAGE_SIZE;
        if (totalPage == 0) {
            totalPage = 1;
        }
        pageObj.setTotalPage(totalPage);
        
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        pageObj.setCurrentPage(page);
        
        int fromIndex = (page - 1) * DEFAULT_PAGE_SIZE;
        int toIndex = Math.min(fromIndex + DEFAULT_PAGE_SIZE, totalNum);
        
        if (list != null && !list.isEmpty() && fromIndex < totalNum) {
            pageObj.setList(list.subList(fromIndex, toIndex));
        }
        
        return pageObj;
    }
}