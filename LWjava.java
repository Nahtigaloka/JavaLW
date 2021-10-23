package lwjava;
//односвязный список
class Node<T> {
    T data;
    int num; //номер объекта
    Node next;
    public Node(T data,int num) 
    {
        this.data = data;
        this.num = num;
    }
}


class Container<T> {
    private int count;//кол-во объектов в контейнере
    private Node<T> head;//начало списка
    
    Container(){
        count=0;
        head=null;
    }
    
    //Извлечь кол-во объектов в контейнере
    int getCount(){
        return count;
    }
    //Добавить объект
    void add(T data){
        if(head==null){
        Node newNode=new Node(data,0);
        head=newNode;
        count++;
        return;
        }
        
        Node<T> cur=head;
        while(cur.next!=null)
            cur=cur.next;
       
        
        Node newNode=new Node(data,cur.num+1);
        cur.next=newNode;
        count++;
    }
    //Добавить объект в указанное место
    void add(T data, int index) {

        Node<T> cur1 = head; 
        int pos = 0;
        Node newNode = new Node(data,index);

        if (count == 0) 
        {
            head = newNode;
            count++;
            return;
        }
        if (index<head.num){
            newNode.next=head;
            head=newNode;
            count++;
            return;
        }
        if (index>count){
            while(cur1.next!=null)
                cur1=cur1.next;
            cur1.next=newNode;
            count++;
            return;
        }
        Node<T> cur2 = head;
        cur2=cur2.next;
        while (cur1.next != null) {
            if (pos == index-1) {
                if(index<cur2.num){
                    cur1.next=newNode;
                    newNode.next=cur2;
                    count++;
                }
                else{
                    System.out.println("Error, this index is already taken");
                }
                return;
            }
            pos++;
            cur2 = cur2.next;
            cur1 = cur1.next;
        }
        
        newNode.next = null; 
        cur2.next = newNode; 
        count++;
    }
    //Извлечь с удалением из контейнера по номеру в контейнере
    T pop(int index){
        T RetData;
        if (head.num==index){
            RetData=head.data;
            head=head.next;
            count--;
            return RetData;
        }
        Node<T> cur = head;
        while (cur.next!=null) {
            if(cur.next.num==index)
                break;
            cur = cur.next;
        }
        
        if(cur.next.next==null){
            Node<T> CurNew=cur.next;
            RetData=CurNew.data;
            cur.next=null;
            count--;
            return RetData;
        }
        if(cur.next.num==index){
        Node<T> CurNew=cur.next;
        RetData=CurNew.data;
        cur.next=cur.next.next;
        count--;
        return RetData;
        }
        return null;
    }
    //Удаление из контейнера
    void del(T data){
        Node<T> cur = head;
        if (head.data==data){
            head=head.next;
            count--;
            return;
        }
        while (cur.next!=null) {
            if(cur.next.data==data)
                break;
            cur = cur.next;
        }
        if(cur.next!=null){
            if(cur.next.data==data){
                if(cur.next.next==null){
                    cur.next=null;
                    count--;
                    return;
                }
            cur.next=cur.next.next;
            count--;
            }
        }
        else{
            System.out.println("Error");
        }
    }
    //Извлечь объект по его номеру в контейнере
    T get(int index){
        int pos=0;
        T RetData;
        Node<T> cur = head;
        if (index == 0) 
        {
           RetData=cur.data;
           return RetData;
        }
        while (cur.next != null) {
            if (cur.num == index) {
                break;
            }
            pos++;
            cur = cur.next;
        }
        
        RetData=cur.data;
        return RetData;
    }
    //Вывод всего контейнера
    void output(){
        System.out.println("Container: ");
        Node<T> cur=head;
        while(cur!=null){
            System.out.print("("+cur.data + ", "+cur.num+")");
            cur=cur.next;
        }
        System.out.println("");
    }
}


public class LWjava {
    public static void main(String[] args) {
       Container container=new Container();
       //тест методов
       int var1=123;
       double var2=24.123;
       String var3="Scratch";
       container.add(var1);
       container.output();//(123, 0)
       
       container.add(var2,2);
       container.output();//(123, 0)(24.123, 2)
       
       container.add(12);
       container.output();//(123, 0)(24.123, 2)(12, 3)
       
       container.add(var3,1);
       container.output();//(123, 0)(Scratch, 1)(24.123, 2)(12, 3)
       
       System.out.println(container.get(3));//12
       
       System.out.println(container.pop(2));//24.123
       container.output();//(123, 0)(Scratch, 1)(12, 3)
       
       container.del("Scratch");
       container.output();//(123, 0)(12, 3)
       
       System.out.println(container.getCount());//2
       
    }
}
