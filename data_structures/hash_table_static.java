package data_structures;

import java.util.List;

import interfaces.functional.*;

public class hash_table_static<T>
{
    hash insert;
    hash find;
    T[] data;
    int buffer;
    int length;
    public hash_table_static(hash insert, hash find,int length) {
        this.insert = insert;
        this.find = find;
        this.length = length;
        this.buffer = (int)(length * 0.2);
        data = (T[])new Object[length + buffer];
    }
    public void add(List<T> value)
    {
        for (T i : value) 
        {
            add(i);
        }
    }
    public void add(T... value)
    {
        for (T i : value) 
        {
            int hash = insert.hash(i);
            if(data[hash] != null)
            {
                for (int j = buffer; j < buffer+length; j++) 
                {
                    if(data[j]==null)
                        data[j]=i;
                }
            }
            else
            {
                data[hash] = i;
            }
                
        }
        
    }
    public T find(Object value)
    {
        return data[find.hash(value)];
    }
}
