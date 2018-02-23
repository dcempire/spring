package com.piglite.Dao;


import com.piglite.model.Question;
import com.piglite.service.QuestionService;
import com.piglite.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("questionService")
@Transactional
public class QuestionDao implements QuestionService{
    static private Session session;
    static private Transaction tx;
    static {
        session = HibernateUtil.currentSession();
        tx = session.beginTransaction();
    }

    public void Close(){
        //通过工具类关闭Session
        tx.commit();
        HibernateUtil.closeSession() ;
    }

    public void saveQuestion(Question question) {
        session.persist(question);
        session.flush();
    }

    public List<Question> findAllQuestions() {
        List<Question> list = session.createQuery("from Question").getResultList();
        return list;
    }

    public Question findById(int id) {
        Question question = session.get(Question.class,id);
        return question;
    }

    public List<Question> findByName(String name) {
        String queryString = "from Question where qname like :name";
        List<Question> question = session.createQuery(queryString)
                .setParameter("name","%"+name+"%").getResultList();
        return question;
    }

    public void updateQuestion(Question question) {
        session.persist(question);
        session.flush();
    }

    public void delById(int id) {
        Question question = findById(id);
        if(question!=null) {
            session.delete(question);
            session.flush();
        }
    }

}
