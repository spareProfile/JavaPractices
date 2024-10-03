package ru.baranova.NauJava.repository;

public interface CrudRepository<T, ID> {
    void create(T object);
    
    T read(ID id);

    void update(T object);

    void delete(ID id);

}
