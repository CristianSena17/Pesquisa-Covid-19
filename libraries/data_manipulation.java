package libraries;


import java.util.*;
import java.text.SimpleDateFormat;

import entities.covid;
import interfaces.functional.comparation;

public class data_manipulation {
	
	
	//Sequencial seach
	public static List<covid> select_by_region(List<covid> list, String estado, String cidade)
	{
		int cont=0;
		List<covid> ret = new ArrayList();
		for (covid i : list) 
		{
			cont+=2;
			if (i.estado.equals(estado) && i.municipio.equals(cidade))
				ret.add(i);
		}
		System.out.printf("Numero de comparacoes da pesquisa sequncial: %d\n",cont);
		return ret;
	} 
	public static void sort_by_date(List<covid> map)
	{
		sort(new ArrayList<Object>(map),(a,b)->{
			try
			{
				return ((covid)a).data.compareTo(((covid)b).data);
			}
			catch(Exception ex)
			{
				return 0;
			}
		});
	}
	public static void sort(List<Object> list, comparation c) {
		if (list.size() < 2) {
		  return;
		}
		int mid = list.size()/2;
		List<Object> left = new ArrayList<Object>(list.subList(0, mid));
		List<Object> right = new ArrayList<Object>(list.subList(mid,list.size()));
	
		sort(left,c);
		sort(right,c);
		merge(left, right, list,c);
	  }
	
	  private static void merge(
		  List<Object> left, List<Object> right, List<Object> list, comparation c) {
		int leftIndex = 0;
		int rightIndex = 0;
		int listIndex = 0;
	
		while (leftIndex < left.size() && rightIndex < right.size()) {
		  if (c.compare(left.get(leftIndex),right.get(rightIndex)) < 0) {
			list.set(listIndex++, left.get(leftIndex++));
		  } else {
			list.set(listIndex++, right.get(rightIndex++));
		  }
		}
		while (leftIndex < left.size()) {
		  list.set(listIndex++, left.get(leftIndex++));
		}
		while (rightIndex < right.size()) {
		  list.set(listIndex++, right.get(rightIndex++));
		}
	  }
	
	//Binary seach
	public static covid get_by_date(List<covid> arr, String x){
		int cont=0;
		try
		{

		int l = 0, r = arr.size() - 1;
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		Date xd = s.parse(x);
		while (l <= r) {
			cont+=2;
			int m = l + (r - l) / 2;
			int cmp = arr.get(m).data.compareTo(xd);
			// Check if x is present at mid
			cont++;
			if (cmp == 0){
				System.out.printf("Numero de comparacoes da pesquisa binaria: %d\n",cont);
				return arr.get(m);
			}

			// If x greater, ignore left half
			cont++;
			if (cmp < 0)
				l = m + 1;

			// If x is smaller, ignore right half
			else
				r = m - 1;
		}

		// if we reach here, then element was not present
		System.out.printf("Numero de comparacoes da pesquisa binaria: %d\n",cont);
		return null;
		}
		catch(Exception ex)
		{
			System.out.printf("Numero de comparacoes da pesquisa binaria: %d\n",cont);
			return null;
		}

	}
}
