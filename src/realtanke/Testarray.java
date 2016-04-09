package realtanke;

import java.util.ArrayList;
import java.util.Iterator;

public class Testarray {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		ArrayList A=new ArrayList<String>();
		A.add("jfld");
		A.add("jfl");
		A.add("jflfd");
		A.add("jfltt");
		A.add("jfl12");
		Iterator i= A.iterator();
		while(i.hasNext()){
			if((String)i.next()=="jfl")
			i.remove();		
		}
		System.out.println(A);
		for(int j=0;j<A.size();j++){
			if(A.get(j)=="jfl")
				A.remove("jfl");
		}
		System.out.println(A);
	}
	
}
