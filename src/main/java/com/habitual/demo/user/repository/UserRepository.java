package com.habitual.demo.user.repository;

import com.habitual.demo.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 数据访问层JPA 用户
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String userName);

    @Query("SELECT u FROM UserEntity u WHERE " +
            "(:username IS NULL OR u.username = :username) AND " +
            "(:nickName IS NULL OR u.nickName = :nickName) AND " +
            "(:sex IS NULL OR u.sex = :sex) AND " +
            "(:age IS NULL OR u.age = :age) AND " +
            "(:email IS NULL OR u.email = :email) AND " +
            "(:tel IS NULL OR u.tel = :tel) AND " +
            "(:role IS NULL OR u.role = :role) AND " +
            "(:status IS NULL OR u.status = :status)")
    Page<UserEntity> findByCriteria(@Param("username") String username,
                                    @Param("nickName") String nickName,
                                    @Param("sex") String sex,
                                    @Param("age") Long age,
                                    @Param("email") String email,
                                    @Param("tel") String tel,
                                    @Param("role") String role,
                                    @Param("status") Long status,
                                    Pageable pageable);

}
