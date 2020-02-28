/**
 * Author: Dylan Lang
 * Class ID: 341
 * Class : Caliss CSE 360 W Spring 2020
 * Assignment #2, Simple List Modification
 * This program creates a simple list
 * and has several functions to modify the list
 * This program is a modification of my first assignment
 */
package cse360assign2;
import java.util.Arrays;

/**
 * SimpleList is the base class of this SimpleList program
 * Note: we do not have a main file so we cannot run it
 * directly. I use JUnit test cases to verify logic 
 * @author Dylan
 *
 */
public class SimpleList {
	// List is our array
	private int[] list;
	// count is our count on how many numbers exist in this array
	private int count;
	// our only class in this project
	public SimpleList(){
		list = new int[10]; // create a new empty list
		count = 0; // force count = to 0
	}
	/**
	 * this method adds a user provided value to our list.
	 * 
	 * @param value integer we want to add to the list 
	 */
	public void add(int value) {
		list = adder(list, value);// adder helper class, result overrides List
		count++;// increment count
	}
	/**
	 * this method similar to add, but value added at end.
	 * 
	 * @param value integer we want to add to the list 
	 */
	public void apppend(int value) {
		list = appender(list, value);// appender helper class, result overrides List
		count++;// increment count
	}
	/**
	 * this method removes a user provided value from our list, if it is present in List
	 *
	 * @param value integer value we want to remove, if present in our list
	 */
	public void remove(int value) {
		for(int i=0; i<count; i++) {// only search up to count, as we know nothing exists beyond count
			if(list[i] == value) {// check if the value in list = user provided value
				list = remover(list, i); // remover helper class, result overrides List
				// above we provide the helper with the position to remove
				count--;// decrement count
			}
		}
	}
	
	/**
	 * private so we can clean up any function that requires us to adjust array size.
	 * Here we supply our input array, calculate the size and make changes if required by the progam guidelines.
	 * This is a helper class that either shrinks or increases the array size.
	 * @param inputArray - array we may change size of
	 * @param typeChange - type of change 1 for increase, 2 for decrease, else doesn't work
	 * @return
	 */
	private int sizeChange(int[] inputArray, int typeChange) {
		int arraySize = inputArray.length; // size of our passed input array
		int newSize = 0; // initializing variable
		double sizeChanger = 0; // initializing variable
		if(typeChange == 1) { // increase array size
			if(count == arraySize) {
				newSize = (int)arraySize/2; //typecast int so never decimal value
				newSize += arraySize;
				arraySize = newSize; // array size will change from initially 10 to 15 now
			}
		}
		else if(typeChange == 2) { // decrease array size
			sizeChanger = arraySize*.75; // decrease of 75% of totalsize
			newSize = (int)sizeChanger; // typecase to int so newSize can be modified
			if(newSize >= count) // if the size satisfies what needs to be change
				arraySize = newSize; // arraySize is changed
		}
		return arraySize;
	}
	/**
	 * private so we can clean up our Add function.
	 * adder helper class, we are able to add a value.
	 * @param inputArray array to add value to
	 * @param inputNumber value to add
	 * @return List, with value added
	 */
	private int[] adder(int[] inputArray, int inputNumber) {
		int arraySize = sizeChange(inputArray,1); // arraysize is used as  the size is no longer static (10) using sizeChange helper class
		/*
		 * Using ArraySize as the size of array is no longer forced to be 10
		 */
		int[] holdArray = new int[arraySize]; //array act as copy of input array
		int[] finalArray = new int[arraySize] ;// array acts as final array we return
		
		for(int i=0; i<count; i++) // copy our inputed array to hold array
			holdArray[i] = inputArray[i];
		finalArray[0] = inputNumber; // add provided value in spot 0 of array
		for(int i=0; i<(arraySize-1); i++) // copy hold array to starting at spot 1
			 finalArray[i+1] = holdArray[i];
		return finalArray;
	}
	/**
	 * similar to the  private adder function above.
	 * Only difference is adding the value at the end of the array
	 * @param inputArray
	 * @param inputNumber
	 * @return
	 */
	private int[] appender(int[] inputArray, int inputNumber){
		int arraySize = sizeChange(inputArray,1); // arraysize is used as  the size is no longer static (10) using sizeChange helper class
		int[] finalArray = new int[arraySize]; // array acts as final array we return		
		for(int i=0; i<count; i++) // copy our inputed array to hold array
			finalArray[i] = inputArray[i];
		finalArray[count] = inputNumber; //count before increment is the position we want to add the value to
		return finalArray;
	}
	/**
	 * private so we can clean up our Remove function.
	 * remover helper class, we are able to remove a value.
	 * @param inputArray array to remove value from
	 * @param positionValue position of value to remove
	 * @return List, with value removed
	 */
	private int[] remover(int[] inputArray, int positionValue) {
		int arraySize = sizeChange(inputArray,2);// arraysize is used as  the size is no longer static (10) using sizeChange helper class
		int[] finalArray = new int[arraySize];  // array acts as final array we return
		if(positionValue == 0){// we act differently if position is at 0.
			for(int i=1; i<arraySize-1; i++)// we shift array 1 position down
				finalArray[i-1] = inputArray[i];
			return finalArray;
		}
		else { // for all other functions
			for(int i=0; i<positionValue; i++)// we copy array upto the position
				finalArray[i] = inputArray[i];
			for(int i=positionValue+1; i<arraySize-1; i++)// then at position we shift the remaining array down.
				finalArray[i-1] = inputArray[i];
			return finalArray;
		}
	}
	/**
	 * return the count of the number of item in our array
	 * @return count of number of item in our array
	 */
	public int count() {
		return count;
	}
	/**
	 * overriding actual toString class
	 * Output created string of array
	 * @return string of array. As an integer array for this class is 10 elements, an empty array would return: 
	 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0] 
	 * at initialization 
	 */
	public String toString() {
		String result = Arrays.toString(list); // Using Arrays method toString to output described value above
		return result;
	}
	/**
	 * Searching for a specific value in the array
	 * @param searchNum value we are searching for
	 * @return the position in array of provided searchNum
	 * 	if not present returns -1
	 */
	public int search(int searchNum) {
		for(int i=0; i<count; i++)
			if(list[i] == searchNum) // if the number we are searching for is present return it, if not return -1
				return i;
		return -1;
	}
	/**
	 * Return the first element in the list.  If there are no elements in the list, I return -1.
	 * @return first value of list
	 */
	public int first() {
		int returnValue = -1; // set return value to -1
		if(count != 0) {
			returnValue = list[0]; // change return value IFF is count is not 0
		}
		return returnValue;	
	}
	/**
	 * Last Return the last element in the list.  If there are no elements in the list, I return -1.
	 * @return last value of list
	 */
	public int last() {
		int returnValue = -1; // set return value to -1
		if(count != 0) {
			returnValue = list[count-1]; // change return value IFF is count is not 0
		}
		return returnValue;	
	}
	/**
	 * Size returns the current number of possible locations in the list
	 * @return the number of empty indexes in our list array
	 */
	public int size() {
		int returnValue = list.length - count; // our return value is length of list - count, so all empty spots in list
		return returnValue;
	}
}
