package at.yoerg.util;

import java.util.ArrayList;
import java.util.Collection;

public abstract class CollectionUtil {

	public static <T> Collection<T> copy(Collection<T> collection) {
		if(collection == null) {
			throw new IllegalArgumentException("collection can not be null");
		}
		Collection<T> coll = new ArrayList<T>();
		for(T elem : collection) {
			coll.add(elem);
		}
		return coll;
	}
	
}
