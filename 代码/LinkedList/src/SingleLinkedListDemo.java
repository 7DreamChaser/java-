public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero2);
        singleLinkedList.update(new HeroNode(2,"2","2"));
        HeroNode hero = singleLinkedList.delete(2);
        System.out.println(hero);
        singleLinkedList.list();
        System.out.println(SingleLinkedList.sum(singleLinkedList.getHead()));
        //测试返回倒数第k个节点
        System.out.println(SingleLinkedList.searchk(singleLinkedList.getHead(),2));
        //反转列表
        SingleLinkedList.reverseLinkedList(singleLinkedList.getHead());
//        new_single.list();


    }
}


//定义SingleLinkedList来管理我们的节点
class SingleLinkedList{
    //先初始化一个头节点，不用来存储数据
    private HeroNode head = new HeroNode(0,"","");

    //添加节点到单向链表
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public HeroNode getHead() {
        return head;
    }

    //另一种添加英雄的方式
    public void addByOrder(HeroNode hero){
        HeroNode temp = head;
        boolean flag = false;//标志添加的编号是否存在，默认为false
        while(true){
            if(temp.next==null){
                //说明temp已经在链表末尾
                break;
            }
            if (temp.next.no>hero.no){
                break;
                //位置找到，放到temp的后面
            }else if(temp.next.no == hero.no){
                //说明编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;
        }


        if(flag){
            //不能添加，编号已存在
            System.out.printf("准备插入的英雄的编号%d已经存在,不能加入\n",hero.no);
        }else{
            //插入到链表中
            hero.next = temp.next;
            temp.next = hero;
        }
    }

    //修改节点信息
    public void update(HeroNode hero){
        //首先判断链表是否为空
        if(head.next == null)
        {
            System.out.println("链表为空~");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;//表示是否找到该节点
        while (true){
            if(temp.next == null){
                System.out.println("链表没有该元素");
                //flag = false;
                break;
            }
            else if(temp.no == hero.no){
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag，判断是否找到需要修改的节点
        if(flag){
            temp.name = hero.name;
            temp.nickname = hero.nickname;
        }else{
            System.out.printf("没有找到编号%d的节点",hero.no);
        }
    }

    //删除节点
    public HeroNode delete(int dno){
        if(head == null){
            System.out.println("链表为空~");
        }
        HeroNode temp = head;
        boolean flag = false;//是否找到需要删除的节点
        while (true){
            if(temp.next == null){
                //到链表尾
//                flag = false
                break;
            }
            else if(temp.next.no == dno){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            //找到节点,删除节点即可
            HeroNode dhero = temp.next;
            temp.next = temp.next.next;
            return dhero;

        }
        else {
            return null;
        }
    }

    //获取单链表的有效元素个数
    public static int sum(HeroNode head){
        //头节点需要去掉
        if(head.next == null)return 0;
        HeroNode temp = head;
        int cal = 0;
        while(temp.next != null){
            cal++;
            temp = temp.next;
        }
        return cal;
    }

    //查找单链表中的倒数第k个节点【新浪面试题】
    public static HeroNode searchk(HeroNode head,int k){
        int num = SingleLinkedList.sum(head)  + 1 - k;
        if(head.next == null)return null;
        HeroNode temp = head;
        if(num<0){
            System.out.println("k的数值不规范");
            return null;
        }
        else {
            for (int i = 0; i < num; i++) {
                temp = temp.next;
            }
            return temp;
        }
//        return null;
    }

    //单链表的反转【腾讯面试题】
    public static void reverseLinkedList(HeroNode head){
        HeroNode reserseHead = null;

        HeroNode cur = head; //temp=1
//        HeroNode temp = new HeroNode();
        HeroNode temp;

        while (cur != null){
//            if(reserseHead.next == null){
//                HeroNode h0 = temp;
//                h0.next = null;
//                reserseHead.next = h0;
//            }
//            else {
            temp = cur.next;
            cur.next = reserseHead;

            reserseHead = cur;
            cur = temp;
//            head.next = null;
//
//            head.next = temp.next;
//            HeroNode h1 = temp;
//            h1.next = reserseHead.next;
//            reserseHead.next = h1;
//            }

//            temp = temp.next;
        }

       HeroNode h = reserseHead;
        while (h !=null){
            System.out.println(h);
            h = h.next;

        }
//
//        return s;
    }

    //显示列表
    public void list(){
        //先判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        while(true){
            if(temp.next == null){
                break;
            }

            temp = temp.next;
            System.out.println(temp);
        }
    }
}

class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode() {
    }

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
     @Override
     public String toString(){
        return "HeroNode[no = "+no+",name = "+name+",nickname = "+nickname+"]";

     }


}
