import myList.MyArrayList;
import myList.MyList;

public class Main {

  public static void main(String[] args) {

    MyList<String> myListOfArray = new MyArrayList<>();

    // add
    myListOfArray.add("asd000");
    myListOfArray.add("asd001");
    myListOfArray.add(2, "asd002");
    myListOfArray.add("asd003");
    myListOfArray.add(4, "asd004");
    myListOfArray.add("asd005");

    // contains
    System.out.println("contains ---> " + myListOfArray.contains("asd005"));
    // size
    System.out.println("size ---> " + myListOfArray.size());
    // set
    myListOfArray.set(0, "0000");
    // get
    System.out.println("get ---> " + myListOfArray.get(0));
    // indexOf
    System.out.println("index of ---> " + myListOfArray.indexOf("asd004"));

    // remove
    myListOfArray.remove(1);
    // to String
    System.out.println(myListOfArray);
    // remove all
    myListOfArray.removeAll();
    System.out.println(myListOfArray);


  }
}
