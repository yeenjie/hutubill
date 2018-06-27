package dao;

import java.util.List;

public abstract class DAO {
    abstract public int getTotal();
    abstract public <T> T get(int id);
    abstract public <T> T update(Class<T> entity);
    abstract public <T> void add(Class<T> entity);
    abstract public <T> List<T> list (int start,int count);
    abstract public <T> List<T> list();
}
