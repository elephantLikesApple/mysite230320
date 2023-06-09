package com.mysite.sbb.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String s);
    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);

    // @Modifying // 만약 아래 쿼리가 UPDATE, DELETE, INSERT 라면 이걸 붙여야 한다.
    // nativeQuery = true 여야 MySQL 쿼리문법 사용 가능
    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE question AUTO_INCREMENT = 1", nativeQuery = true)
    void clearAutoIncrement();

    Page<Question> findAll(Pageable pageable);
}
