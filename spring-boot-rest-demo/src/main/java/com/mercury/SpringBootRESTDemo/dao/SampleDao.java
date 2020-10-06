package com.mercury.SpringBootRESTDemo.dao;

import com.mercury.SpringBootRESTDemo.bean.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

//generics -- before in hibernate, we have many Dao classes like bookDao SampleDao,
// most of the codes are duplicate!

//1st: entity class(CRUD)
//2nd: id field
public interface SampleDao extends JpaRepository<Sample, String> {

}

//Spring Data JPA will create a class to implement our sampleDao Interface
//in order to provide methods for CRUD sample
// that class will contains all the methods SampleDao inherits from Jpa Resporitroy
