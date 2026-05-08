package com.springinpractice.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.util.ReflectionUtils; // springframwork -> org.springframework
import com.springinpractice.dao.Dao;

// 1. abatract -> abstract, object -> Object
public abstract class AbstractHbnDao<T extends Object> implements Dao<T> {

    @Inject 
    private SessionFactory sessionFactory;

    private Class<T> domainClass;

    // 2. 하이버네이트 세션 획득 로직 수정
    protected Session getSession() {
        return sessionFactory.getCurrentSession(); // SessionFactory -> sessionFactory(변수명)
    }

    // 3. 리플렉션을 이용해 제네릭 클래스 타입(T)을 결정하는 로직 완성
    @SuppressWarnings("unchecked") // SuppresWarnings -> @SuppressWarnings
    private Class<T> getDomainClass() {
        if (domainClass == null) {
            ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
            thisType.getActualTypeArguments();
            this.domainClass = (Class<T>) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    // 4. Dao 인터페이스 구현체 메서드 예시
    @Override
    public void create(T t) {
        getSession().save(t);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(Serializable id) {
        return (T) getSession().get(getDomainClass(), id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return getSession()
                .createQuery("from " + getDomainClass().getName())
                .list();
    }

    @Override
    public void delete(T t) {
        getSession().delete(t);
    }

    @Override
    public void deleteById(Serializable id) {
        T obj = get(id);
        if (obj != null) {
            delete(obj);
        }
    }

    @Override
    public void deleteAll() {
        getSession()
                .createQuery("delete from " + getDomainClass().getName())
                .executeUpdate();
    }

    @Override
    public long count() {
        return (Long) getSession()
                .createQuery("select count(*) from " + getDomainClass().getName())
                .uniqueResult();
    }

    @Override
    public boolean exists(Serializable id) {
        return get(id) != null;
    }
}
