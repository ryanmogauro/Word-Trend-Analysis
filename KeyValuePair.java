
/*
 * Ryan Mogauro
 * 4/11/22
 * KeyValuePair.java
 * Word Frequencies
 * CS231
 */
public class KeyValuePair <Key, Value>{
	private Key key;
	private Value val; 
	// the constructor initializing the key and value fields.
	public KeyValuePair( Key k, Value v ) {
		this.key = k; 
		this.val = v;
	}
	
	//returns the key.
	public Key getKey() {
		return this.key; 
	}
	
	//returns the value.
	public Value getValue(){
		return this.val; 
	}
	
	//sets the value.
	public void setValue(Value v){
		this.val = v; 
	}
	
	
	//returns a String containing both the key and value
	public String toString() {
		return this.key + " = " + val; 
	}
	
	//tests methods to ensure they're working properly
	public static void main( String[] argv ) {
		 KeyValuePair<String, Integer> KVPair = new KeyValuePair<String,Integer>("hi",5); 
		 System.out.println(KVPair.getKey()); 
		 System.out.println(KVPair.getValue()); 
		 System.out.println(KVPair); 
		 KVPair.setValue(6);
		 System.out.println(KVPair);
		 
	}
}
