/*
 * Ryan Mogauro
 * 4/11/22
 * BSTMap.java
 * Word Frequencies
 * CS231
 */
import java.util.ArrayList;
import java.util.Comparator;

public class BSTMap<K, V> implements MapSet<K, V> {

	private Comparator<K> comp; 
	private TNode<K,V> root;
	private int size; 

	// constructor: takes in a Comparator object
	public BSTMap(Comparator<K> comp ) {
		this.comp = comp; 
		this.root = null; 
		this.size = 0; 
		}

	// adds or updates a key-value pair
	// If there is already a pair with new_key in the map, then update
	// the pair's value to new_value.
	// If there is not already a pair with new_key, then
	// add pair with new_key and new_value.
	// returns the old value or null if no old value existed
	public V put( K key, V value ) {
				if(this.root == null) {
					this.root = new TNode<K,V>(key, value); 
					this.size++; 
					return null; 
				}
				V result = this.root.put(key, value, this.comp); 
				if(result == null) {
					this.size++; 
				}
				return result; 
        }

    // gets the value at the specified key or null
    public V get( K key ) {
            // check for and handle the special case
            // call the root node's get method
    		if(!(this.containsKey(key))) {
    			return null; 
    		}
    		return this.root.get(key, this.comp); 
    }
    
    //returns a boolean based on if the given key parameter exists within the tree
    public boolean containsKey(K key) {
    	if(this.root == null) {
    		return false; 
    	}
    	return this.root.containsKey(key, this.comp); 
    }

    
    //returns an ArrayList that contains all of the keys in the map
    public ArrayList<K> keySet(){
    	if(this.root == null) {
    		return null; 
    	}
    	ArrayList<K> list = new ArrayList<K>(); 
    	return this.root.keySet(list); 
    }
    //creates and returns an arraylist of the values in the tree
    public ArrayList<V> values(){
    	if(this.root == null) {
    		return null; 
    	}
    	ArrayList<V> list = new ArrayList<V>(); 
    	return this.root.values(list); 
    }
    
    
    //returns an ArrayList of KeyValuePair objects
    public ArrayList<KeyValuePair<K,V>> entrySet(){
    	if(this.root == null) {
    		return null;
    	}
    	ArrayList<KeyValuePair<K,V>> list = new ArrayList<KeyValuePair<K,V>>(); 
    	return this.root.entrySet(list); 
    }
    
    
    //returns number of nodes in the tree
    public int size() {
    	return this.size; 
    }
    
    //resets the tree 
    public void clear() {
    	this.root = null; 
    	this.size = 0;
    }
    
    //returns String representing node
    public String toString() {
    	return this.root.toString(); 
    }
    // You can make this a nested class
    private class TNode<K,V> {
    		private KeyValuePair<K,V> data; 
            private TNode<K,V> left, right; 
           
            // need a KeyValuePair to hold the data at this node

            // constructor, given a key and a value
            public TNode( K k, V v ) {
                    // initialize all of the TNode fields
            	this.data = new KeyValuePair<K,V>(k,v); 
            	this.left = null; 
            	this.right = null; 
            }

            // Takes in a key, a value, and a comparator and inserts
            // the TNode in the subtree rooted at this node 

			// Returns the value associated with the key in the subtree
			// rooted at this node or null if the key does not already exist
            public V put( K key, V value, Comparator<K> comp ) {
                    int result = comp.compare(key, this.data.getKey()); 
                    if(result == 0) {
                    	V oldVal = this.data.getValue();
                    	this.data.setValue(value);
                    	return oldVal; 
                    }
                    if(result < 0) {
                    	if(this.left == null) {
                    		this.left = new TNode<K,V>(key,value); 
                    		return null;
                    	}
                    return this.left.put(key, value, comp);
                    }
                    if(result > 0) {
                    	if(this.right == null) {
                    		this.right = new TNode<K,V>(key,value); 
                    		return null;
                    	}
                    return this.right.put(key, value, comp);
                    }
                    return null; 
            }

            // Takes in a key and a comparator
            // Returns the value associated with the key or null
            public V get( K key, Comparator<K> comp ) {
            	int result = comp.compare(key,this.data.getKey()); 
            	if(result == 0) {
            		return this.data.getValue(); 
            	} else if(result < 0) {
            		return this.left.get(key, comp); 
            	}
            	return this.right.get(key, comp); 
            }

            // Any additional methods you want to add below, for
            // example, for building ArrayLists
            
            public boolean containsKey(K key, Comparator<K> comp) {
            	TNode<K,V> current = this; 
            	while(current!=null) {
            		int result = comp.compare(key,  current.data.getKey()); 
            		if(result == 0) {
            			return true; 
            		} else if(result < 0) {
            			current = current.left; 
            		} else if(result > 0) {
            			current = current.right; 
            		}
            	}
            	return false; 
            }
            
            //returns an ArrayList of KeyValuePair objects
            public ArrayList<KeyValuePair<K,V>> entrySet(ArrayList<KeyValuePair<K,V>> list){
            	list.add(this.data); 
            	if(this.left != null) {
            		this.left.entrySet(list); 
            	}
            	if(this.right != null) {
            		this.right.entrySet(list); 
            	}
            	return list; 
            }
            
            //returns an ArrayList that contains all of the keys in the map
            public ArrayList<K> keySet(ArrayList<K> list){
            	list.add(this.data.getKey()); 
            	
            	if(this.left == null){
            		this.left.keySet(list); 
            	}
            	if(this.right != null) {
            		this.right.keySet(list);
            	}
            	
            	return list; 
            }
            
            //returns an arraylist
            public ArrayList<V>values(ArrayList<V> list){
            	list.add(this.data.getValue()); 
            	if(this.left != null) {
            		this.left.values(list); 
            	}
            	if(this.right != null) {
            		this.right.values(list); 
            	}
            	return list;
            }
            
            //returns string representing tree
            public String toPrint(int level) {
            	String str = ""; 
            	String tabs = "\t"; 
            	str+=this.data;
            	
            	for(int i = 0; i < level; i++) {
            		tabs+=tabs; 
            	}
            	if(this.left != null) {
            		str+="\n"; 
            		str+="left:" + tabs; 
            		str+=this.left.toPrint(level+1); 
            	}
            	if(this.right != null) {
            		str+="\n"; 
            		str+="right:" + tabs; 
            		str+= this.right.toPrint(level + 1); 
            	}
            	
            	return str; 
            }
            
            
            
            
    }// end of TNode class

    // test function
    public static void main( String[] argv ) {
            

            // create a BSTMap
            BSTMap<String, Integer> bst = new BSTMap<String, Integer>( new AscendingString() );

            bst.put( "twenty", 20 );
            bst.put( "ten", 10 );
            bst.put( "eleven", 11 );
            bst.put( "five", 5 );
            bst.put( "six", 6 );

            System.out.println( bst.get( "eleven" ) );
            
            System.out.println( bst.get( "four" ) );
            
            System.out.println( bst.get( "six" ) );
            
            System.out.println( bst.containsKey("six"));

            // put more test code here

           
    }

}