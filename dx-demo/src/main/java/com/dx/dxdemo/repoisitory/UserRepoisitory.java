package com.dx.dxdemo.repoisitory;

import com.dx.dxdemo.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @author tWX579831
 * @date 2020/3/19 23:01
 */
public interface UserRepoisitory extends JpaRepository<User, Integer> {
    User findByTelnum(Long telnum);

    User findByTelnumAndPassword(Long telnum, String password);

    @Query(value = "select * from user", nativeQuery = true)
    Page<User> findUserByPage(PageRequest pageable);

    List<User> findAllByTelnum(Long telnum);
}
