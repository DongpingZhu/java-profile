package com.test.mybatis.repository;

import com.test.mybatis.bean.Userz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserzDao extends JpaRepository<Userz,Long> {
    Userz findByUsernameAndPassword(String username, String password);
    Userz save(Userz userz);
}
