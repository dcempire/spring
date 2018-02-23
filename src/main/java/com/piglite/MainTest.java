package com.piglite;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.piglite.Dao.QuestionDao;
import com.piglite.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import com.piglite.model.Question;
import org.hibernate.service.ServiceRegistry;

import javax.management.Query;


public class MainTest {
    public static void main(String[] args) {
        QuestionDao qdao = new QuestionDao();
        Question q1 = new Question();
        q1.setQname("China 是什么?");
        qdao.saveQuestion(q1);

        List<Question> dats = qdao.findAllQuestions();
        for(Question question:dats){
            System.out.println("data:" + question.getQname());
        }
        dats = qdao.findByName("Java");
        for(Question question:dats){
            System.out.println("data:" + question.getQname());
        }
        qdao.delById(5);
        qdao.Close();
    }

    void t(){
        // 打开线程安全的Session对象
        Session session = HibernateUtil.currentSession();
        // 打开事务
        Transaction tx = session.beginTransaction();
        querydata(session,tx);
        tx.commit();
        //通过工具类关闭Session
        HibernateUtil.closeSession();
    }

    private  static void querydata(Session session,Transaction t){
        // 查询数据
        List<Question> list = session.createQuery("from Question").getResultList();

        for(Question q:list){
            System.out.println("Question Name: " + q.getQname());
            List<String> la = q.getAnswers();
            for(String a:la){
                System.out.println(a);
            }
        }
    }
    private static void Adddata(Session session,Transaction t){
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("java is a programming language");
        list1.add("java is a platform");

        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("Servlet is an Interface");
        list2.add("Servlet is an API");

        Question question1 = new Question();
        question1.setQname("Java 是什么?");
        question1.setAnswers(list1);

        Question question2 = new Question();
        question2.setQname("Hibernate 是什么?");
        question2.setAnswers(list2);

        session.persist(question1);
        session.persist(question2);

        t.commit();
    }
}
