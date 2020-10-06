package com.mercury.SpringBootRESTDemo.controller;

import com.mercury.SpringBootRESTDemo.bean.Sample;
import com.mercury.SpringBootRESTDemo.dao.FakeSampleDao;
import com.mercury.SpringBootRESTDemo.dao.SampleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/samples")
public class SampleController {

    @Autowired
    FakeSampleDao fakeSampleDao;
    @Autowired // Autowired 的是实现interface的对象
            SampleDao sampleDao;

    @PreAuthorize("isAuthenticated()") // 方法访问前验证用户
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Sample> getSamples() {
        //return fakeSampleDao.getAllSamples();
        return sampleDao.findAll();
    }
}
