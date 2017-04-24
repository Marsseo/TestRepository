package ch15.exam05;

import java.util.*;

public class HashMapExample {

	public static void main(String[] args) {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("½Å¿ë±Ç", 85);
		map.put("È«±æµ¿", 90);
		map.put("µ¿Àå±º", 80);
		map.put("È«±æµ¿", 95);
		
		System.out.println("ÃÑ Entry ¼ö: "+map.size());
		
		System.out.println("\tÈ«±æµ¿ : " + map.get("È«±æµ¿"));
		System.out.println();
		
		Set<String> ks = map.keySet();
		Iterator<String> keyIterator = ks.iterator();
		while(keyIterator.hasNext()){
			String key = keyIterator.next();
			Integer value = map.get(key);
			System.out.println("\t"+key+" : "+value);
		}
		System.out.println();
		
		map.remove("È«±æµ¿");
		System.out.println("ÃÑ Entry ¼ö: "+map.size());
		
		Set<Map.Entry<String, Integer>> ens = map.entrySet();
		Iterator<Map.Entry<String, Integer>> entryIterator = ens.iterator();
		while(entryIterator.hasNext()){
			Map.Entry<String, Integer> entry = entryIterator.next();
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println("\t"+key+" : "+value);
		}
		System.out.println();
		
		map.clear();
		System.out.println("ÃÑ Entry ¼ö: "+map.size());
		
	}

}
