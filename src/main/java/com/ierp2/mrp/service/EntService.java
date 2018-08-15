package com.ierp2.mrp.service;

import com.ierp2.mrp.dao.EntDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EntService {

    @Autowired
    EntDao entDao;

}
