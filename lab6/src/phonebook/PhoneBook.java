package phonebook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class PhoneBook {
	private Map<String,HashSet<String>> pb;
	private String saveFile;
	
	public PhoneBook(String file) {
		this.saveFile = file + ".bin";
		pb = this.newHashMap();
	}
	
	
	/** 
	 * Associates the specified number with the specified 
	 * name in this phone book. 
	 * post: If the specified name is not present in this phone book,
	 *        the specified name is added and associated  with
	 *  	  the specified number. Otherwise the specified 
	 *  	  number is added to the set of number associated with name.
	 * @param name The name for which a phone number is to be added
	 * @param number The number associated with the specified name
	 */
	public void put(String name, String number) {
		HashSet<String> numbers = pb.get(name);
		if(numbers == null){
			numbers = new HashSet<String>();
			pb.put(name,numbers);
		}
		numbers.add(number);
	}
	
	
	/**
	 * Removes the the specified name from this phone book.
	 * post: If the specified name is present in this phone book,
	 * 		 it is removed. Otherwise this phone book is
	 * 		 unchanged.
	 * @param name The name to be removed.
	 * @return true if the specified name was present.
	 */
	public boolean remove(String name) {
		return pb.remove(name) != null;
	}
	
	/**
	 * Retrieves a list of phone numbers for the specified name. If the 
	 * specified name is not present in this phone book an empty list is 
	 * returned.
	 * @param name The name whose associated phone numbers are to be returned  
	 * @return The phone numbers associated with the specified name
	 */
	public List<String> findNumber(String name) {
		List<String> list = new LinkedList<String>();
		HashSet<String> numbers = pb.get(name);
		if(numbers != null){
			for(String number: numbers){
				list.add(number);
			}
		}
		return list;
	}
	
	/**
	 * Retrieves a list of names associated with the specified phone number. 
	 * If the specified number is not present in this phone book an empty 
	 * list is returned.
	 * @param number The number for which the set of associated
	 * names is to be returned.
	 * @return The list of names associated with the specified number.
	 */
	public List<String> findNames(String number) {
		List<String> names = new LinkedList<String>();
		for(Map.Entry<String,HashSet<String>> pair : pb.entrySet()){
			if(pair.getValue().contains(number)){
				names.add(pair.getKey());
			}
		}
		return names;
	}
	
	/**
	 * Retrieves the set of all names present in this phone book.
	 * The set's iterator will return the names in ascending order
	 * @return The set of all names present in this phone book
	 */
	public Set<String> names() {
		return pb.keySet();
	}
	
	/**
	 * Returns true if this phone book is empty
	 * @return true if this phone book is empty
	 */	
	public boolean isEmpty() {
		return pb.isEmpty();
	}
	
	/**
	 * Returns the number of names in this phone book
	 * @return The number of names in this phone book
	 */
	public int size() {
		return pb.size();
	}

	private HashMap<String,HashSet<String>> newHashMap() {
		try { 
			ObjectInputStream in =
				new ObjectInputStream(new FileInputStream(saveFile));
			return (HashMap<String,HashSet<String>>) in.readObject();
		} catch (Exception ex) {
			return new HashMap<String,HashSet<String>>();
		}
	}
	
	public boolean persist() {
		try {
			 ObjectOutputStream out =
				 new ObjectOutputStream(new FileOutputStream(saveFile));
			 out.writeObject(pb);
			 return true;
		 } catch (Exception ex) {
			 ex.printStackTrace();
			 return false;
		 }
	}
}
