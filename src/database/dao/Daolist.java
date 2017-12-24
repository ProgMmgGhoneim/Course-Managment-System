package database.dao;

import java.util.List;

public interface Daolist<T> {
    public List<T> LoadAll() throws Exception;
    
    public  int  inseret(T t) throws Exception;
    
    public int  update (T t) throws Exception;
    
    public void delet(T t) throws Exception;
    
    public T getdata (T t) throws Exception;
    
}
