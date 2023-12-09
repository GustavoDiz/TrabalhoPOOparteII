package model;

import java.util.ArrayList;
import java.util.List;

public interface DAO<T> {
    public T add(T elemento);
    public ArrayList<T> list();
    public T update(T elemento, int op);
    public T delete(T elemento);

}
