package com.dx.dxdemo.repoisitory;

import com.dx.dxdemo.dao.Wage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author tWX579831
 * @date 2020/3/21 21:19
 */
public interface WageRepository extends JpaRepository<Wage, Integer> {
    List<Wage> findByYearAndUserId(Integer year, Integer userId);

    List<Wage> findByUserId(Integer userId);

    List<Wage> findByUserIdAndYear(Integer userId, Integer year);
}
