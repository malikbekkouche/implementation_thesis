import java.util.TreeMap;
class SyncTree<K,V> extends TreeMap<K,V>{
	public V put(K k,V v){
		synchronized(this){
			return super.put(k,v);
		}
	}
	
	public V remove1(K k){
		synchronized(this){
			return super.remove(k);
		}
	}
	
	public V get1(K k){
		synchronized(this){
			return super.get(k);
		}
	}
}