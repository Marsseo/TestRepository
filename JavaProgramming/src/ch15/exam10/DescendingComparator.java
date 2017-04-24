package ch15.exam10;

import java.util.Comparator;

public class DescendingComparator implements Comparator<Fruit>{
	public int compare(Fruit o1, Fruit o2) {
		return o2.price-o1.price;
	};
}
