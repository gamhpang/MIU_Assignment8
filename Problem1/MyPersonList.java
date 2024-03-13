package Problem1;

import java.util.Arrays;

public class MyPersonList {
    private final int INITIAL_LENGTH = 4;
    private Person[] currentArray;

    private int numOfElements;

    public MyPersonList(){
        currentArray = new Person[INITIAL_LENGTH];
        numOfElements = 0;
    }

    public void add(Person p){
        if(p==null)return;
        if(numOfElements == currentArray.length)
            resize();
        currentArray[numOfElements] = p;
        numOfElements = numOfElements+1;
    }

    public Person get(int i){
        if(i<0||i>=numOfElements){
            return null;
        }
        return currentArray[i];
    }

    private void resize(){
        System.out.println("resizing");
        int len = currentArray.length;
        int newlen = 2 * len;
        Person[] newArray = new Person[newlen];
        System.arraycopy(currentArray,0,newArray,0,len);
        currentArray = newArray;
    }

    public boolean isEmpty(){
        return (numOfElements == 0);
    }

    public boolean find(Person p){
        if(p==null)return false;
        for(int i=0;i<numOfElements;i++){
            if(currentArray[i].getLast().equals(p.getLast()))
                return true;
        }
        return false;
    }

    public  void insert(Person p ,int pos){
        if(p==null)return;
        if(pos>numOfElements || pos<0)return;
        if(numOfElements == currentArray.length){
            resize();
        }
        Person[] temp = new Person[currentArray.length];
        System.arraycopy(currentArray,0,temp,0,pos);
        temp[pos] = p;
        System.arraycopy(currentArray,pos,temp,pos+1,currentArray.length-(pos+1));
        currentArray = temp;
        ++numOfElements;
    }

    public boolean remove(Person p){
        if(numOfElements==0)return false;
        if(p==null)return false;
        int index = -1;
        for(int i=0;i<numOfElements;++i){
            if(currentArray[i].getLast().equals(p.getLast())){
                index = i;
                break;
            }
        }
        if(index==-1)return false;
        Person[] temp = new Person[currentArray.length];
        System.arraycopy(currentArray,0,temp,0,index);
        System.arraycopy(currentArray,index+1,temp, index,currentArray.length-(index+1));
        currentArray = temp ;
        --numOfElements;
        return true;
    }

    public  String toString(){
        StringBuilder sb = new StringBuilder("[");


        for(int i=0;i<numOfElements-1;++i){
            String temp = "Person [lastName=" + currentArray[i].getLast()+"]";
            sb.append(temp+", ");
        }
        String temp = "Person [lastName=" + currentArray[numOfElements -1].getLast()+"]";

        sb.append(temp+"]");
        return sb.toString();
    }
    public int size(){return numOfElements;}
    public Object clone(){
        Person[] temp = Arrays.copyOf(currentArray,numOfElements);
        return temp;
    }

    public static void main(String[] args) {
        MyPersonList l = new MyPersonList();
        l.add(new Person("AA","HH",23));
        l.add(new Person("BB","HH",24));
        l.add(new Person("CC","HH",25));
        l.add(new Person("DD","HH",26));

        l.insert(new Person("EE","GG",29),3);
        System.out.println("At index 3: "+l.get(3));
        l.remove(new Person("AA","HH",24));
        System.out.println(l.toString());
    }
}
