package com.zxc.service;

import com.zxc.model.Page;

import java.util.List;

public interface PageService {
    <T> Page<T> subList(int page, List<T> list);
}