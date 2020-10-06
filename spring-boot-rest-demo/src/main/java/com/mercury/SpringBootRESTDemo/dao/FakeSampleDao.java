package com.mercury.SpringBootRESTDemo.dao;

import com.mercury.SpringBootRESTDemo.bean.Sample;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Conponent
@Repository
public class FakeSampleDao {
    public List<Sample> getAllSamples() {
        List<Sample> sampleList = new ArrayList<>();
        Sample bob = new Sample("bob", 21);
        Sample alice = new Sample("alice", 25);
        Sample alex = new Sample("alex", 31);
        sampleList.add(bob);
        sampleList.add(alice);
        sampleList.add(alex);
        return sampleList;
    }

}
