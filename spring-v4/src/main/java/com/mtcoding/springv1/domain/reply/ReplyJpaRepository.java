package com.mtcoding.springv1.domain.reply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReplyJpaRepository {
    private final EntityManager em;

    public Reply findById(int id) {
        return em.find(Reply.class, id);
    }

    public Reply save(Reply reply) {
        em.persist(reply);
        return reply;
    }

    public void deleteById(int id) {
        Query query = em.createQuery("delete from Reply r where r.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
