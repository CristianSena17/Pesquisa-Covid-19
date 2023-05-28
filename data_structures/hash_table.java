package data_structures;

import java.util.LinkedList;
import java.util.List;

import interfaces.functional.*;

public class hash_table<T>
{
    hash insert;
    hash find;
    comparation compare;
    Object[] data;
    public hash_table(hash insert, hash find, comparation compare,int length) {
        this.insert = insert;
        this.find = find;
        this.compare = compare;
        data = new Object[length];
    }
    public void add(List<T> value)
    {
        for (T i : value) 
        {
            if(i != null)
                add(i);
        }
    }
    public void add(T... value)
    {
        for (T i : value) 
        {
            if(i != null){
                int hash = insert.hash(i);
                if(data[hash] == null)
                {
                    data[hash] = new LinkedList<T>();
                }
                ((List<T>)data[hash]).add(i);
            }
        }
        
    }
    public T find(Object value)
    {
        int cont=0;
        List<T> d = (List<T>)data[find.hash(value)];
    
        for (T i : d) {
            cont += 2;
            if (compare.compare(i, value) == 0) {
                System.out.printf("Numero de comparacoes na Tabela Hash: %d\n", cont);
                return i;
            }

        }
        
        System.out.printf("Numero de comparacoes na Tabela Hash: %d\n",cont);
        return null;
    }
}
