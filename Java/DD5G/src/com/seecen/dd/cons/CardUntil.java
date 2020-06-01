package com.seecen.dd.cons;

import com.seecen.dd.entity.*;
import com.seecen.dd.service.Common;
import com.seecen.dd.service.MyMessage;


import java.io.*;

import java.util.*;

 /**
 * @author ${林锋鹏}
 * @Title: 卡工具类
 * @ProjectName Java
 * @date 2019/8/15 17:10
 */
public class CardUntil{

    static  ObjectFile obj = new ObjectFile();
    Scanner input = new Scanner(System.in);
    //存储用户号码和卡号
    public static Map<String ,MobileCard> cards = new HashMap<>();
    //存储 用户和消费记录
    private static    Map<String ,List<ConsumInfo>> consumInfo = new HashMap<>();
    private   String[] numbers;
    private  static    String[] strings = new String[10];
    //定义几个场景
     static getData data =  new getData();



    Map<Integer, Scene> scenes = new HashMap<Integer, Scene>();
    Scene scene2 = new Scene("通话", 90, "问候客户，谁知其如此难缠，通话90分钟");
    Scene scene1 = new Scene("通话", 30, "询问妈妈身体状况，本地通话30分钟");
    Scene scene0 = new Scene("短信", 5, "参与环境保护实施方案问卷调查，发送短信5条");
    Scene scene3 = new Scene("上网", 10, "在线看电视，不留神睡着啦！使用10GB");
    Scene scene4 = new Scene("上网", 1, "微信视频聊天，使用流量1GB");



    //存储手机号作为key

    public static void init(){
        ArrayList<MobileCard>list = obj.objectInputFile();
        for (MobileCard card: list) {
            cards.put(card.getCardNumber(),card);
        }
    }
        // 判断 是否能登陆
    //  使用5g
    public void useDD(String number) {

        //首先判断是什么套餐，可以选择什么业务
        scenes.put(0, scene0);
        scenes.put(1, scene1);
        scenes.put(2, scene2);
        scenes.put(3, scene3);
        scenes.put(4, scene4);

        MobileCard mobileCard = cards.get(number);

        int consumption = 0;
        ServicePackage serPackage = mobileCard.getSerPackage();
        int i = 0;
        boolean is =false;
         Random random = new Random();
        aa:  do{
            System.out.println("请选择你要选择的业务：1.发短信，2.模拟电话，3.上网，4.查看短信,5.查看系统消息,6.退出");
          ///  i= random.nextInt(5);
            if(cards.get(number).getMailbox()==1){
                System.out.println("您有一条未读短信！");
            }
            if(cards.get(number).getSystemmailbox()==1){
                System.out.println("您有"+cards.get(number).getSystemmailbox()+"系统消息未读！");
            }
            i=input.nextInt();
            Scene scene = scenes.get(i);

            switch (i){
                case 0:
                case 1:
                    System.out.println("请你选择你要发的收信人的手机号码：");
                    String otherPhone = input.next();
                    Set<String> strings = cards.keySet();
                    Iterator<String> iterator = strings.iterator();
                    while (iterator.hasNext()){
                        if(otherPhone.equals(iterator.next())){
                            is =true;
                            break ;
                        }
                    }
                    if (is) {
                        System.out.println("请输入你要发的内容：");
                        String contents = input.next();
                        System.out.println("请输入你要发的条数：");
                        int pieces = input.nextInt();
                        SendService sendService = (SendService) serPackage;
                        try {
                            sendService.send(pieces, mobileCard);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        cards.get(otherPhone).setMessage(contents+"发信人的手机号码："+mobileCard.getCardNumber());
                        cards.get(otherPhone).setMailbox(1);
                        addConsumInfo(mobileCard.getCardNumber(), new ConsumInfo(mobileCard.getCardNumber(), scene.getType(), consumption));
                        System.out.println("发送成功！");
                        break ;

                    } else {
                        System.out.println("你发的对象不存在");
                        continue ;
                    }

                case 2:

                            System.out.println(scene.getDescription());
                            CallService sendService = (CallService) serPackage;

                            try {
                                consumption = sendService.Call(scene.getData(), mobileCard);
                            } catch (Exception e) {
                                e.getMessage();
                            }
                            addConsumInfo(number,new ConsumInfo(mobileCard.getCardNumber(), scene.getType(), consumption));
                            break ;


                case 3:

                           System.out.println(scene.getDescription());
                           NetService netService = (NetService) serPackage;
                           try {
                               consumption = netService.Net(scene.getData(),mobileCard);
                           } catch (Exception e) {
                               e.getMessage();
                           }
                           addConsumInfo(number, new ConsumInfo(mobileCard.getCardNumber(), scene.getType(), consumption));
                           break ;
                case 4:
                    String messge = mobileCard.getMessage();
                    mobileCard.setMailbox(0);
                    System.out.println("您收到的短信是："+messge);
                    break;
                case 5:
                    System.out.println("系统消息：");
                    try  {
                        File file = new File("C:\\Users\\asus\\Desktop\\" +
                                "sc190701\\学员\\林锋鹏\\Java\\DD5G\\src\\系统消息.txt");
                        FileReader fileReader = new FileReader(file);
                        BufferedReader br = new BufferedReader(fileReader);
                        String line =null;
                        while ((line=br.readLine())!=null){
                            System.out.println(line);
                        }
                        cards.get(number).setSystemmailbox(0);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                default: break aa;
            }

        }while (true);

    }

    private void addConsumInfo(String number,ConsumInfo Info) {
        if(consumInfo.containsKey(number)){
            consumInfo.get(number).add(Info);
        }else {
            List<ConsumInfo> consumInfos = new ArrayList<>();
            consumInfos.add(Info);
            consumInfo.put(number,consumInfos);
        }
    }

    public  boolean isExistCard(String number,String password){
        if(cards.containsKey(number)&&cards.get(number).getPassWord().equals(password)){
            return  true;
        }
        //System.out.println("密码错误，登陆失败！");
        return false;

    }
    //判断号码是否存在，充值话费
    public boolean isExistCard(String number){
        Set<String> key = cards.keySet();
        Iterator<String> iterator = key.iterator();
        while (iterator.hasNext()){
           if( iterator.next().equals(number)){
               return false;
           }
    }
        return true;
    }
    //充值话费方法
    public void  reCharge(String number,int money){
        cards.get(number).setMoney(cards.get(number).getMoney()+money);

    }



    //注册号码,创建一个号码
    public String creatNumber(){
        int number;
        while (true) {
           number = ((int) (Math.random() * 100000000));
            String flag = String.valueOf(number);
            if (flag.length() == 8){
                number = Integer.parseInt(flag);
               break;
            }
        }
        return number+"" ;
    }
    //注册号码时得到9个新一串号码
    public String[] getNewNumbers(){
        for (int i = 0; i <strings.length; i++) {
            String s = creatNumber();
            strings[i] = s;

        }
        return  strings;
    }
    public void addCard(String number,MobileCard card){

        cards.put(number,card);
        //注册完序列进去
        obj.objectOutputFile(card);
    }





     //查询这个号码得当前消费套餐情况
     public void  showAmountDetail(String numbers){
        StringBuffer meg = new StringBuffer();

        MobileCard Card = cards.get(numbers);
        System.out.println(Card);
        meg.append("您的卡号是："+Card.getCardNumber()+"当月账单：\n");
        meg.append("资费详情："+Card.getSerPackage().getPrice()+"\n");
        meg.append("合计："+ Card.getSerPackage().getPrice()+"\n");
        meg.append("账户余额："+Common.dataFormat(Card.getMoney())+"\n");
        System.out.println(meg);


    }

    //查询该号码剩余套餐情况
    public void showRemainDetail(String number){
        //获取这个号码的当前信息
        MobileCard mobileCard = cards.get(number);
        System.out.println(mobileCard.hashCode());
        int remainTalkTime;
        int remainSmsCount;
        int remainFlow;
        StringBuffer meg = new StringBuffer();
        //查询套餐信息
        ServicePackage pack = mobileCard.getSerPackage();
        meg.append("您的卡号是："+mobileCard.getCardNumber()+"套餐余量是：\n");
        if(mobileCard.getSerPackage()instanceof TalkPackage){
            TalkPackage cardpack = (TalkPackage)pack;
            if(cardpack.getTalktime()>mobileCard.getRealTakTime()){
                remainTalkTime = cardpack.getTalktime()-mobileCard.getRealTakTime();

            }else {
                remainTalkTime = 0;
            }
            if(cardpack.getSmsCount()>mobileCard.getRealSMSCount()){
                remainSmsCount = cardpack.getSmsCount()-mobileCard.getRealSMSCount();
            }else {
                remainSmsCount = 0;
            }

            meg.append("剩余通话时间："+remainTalkTime+"分钟.\n");
            meg.append("剩余短信条数为："+remainSmsCount);
        }
        if(mobileCard.getSerPackage()instanceof NetPackage){
           NetPackage Cardpack =  (NetPackage)pack;
           if(Cardpack.getFlow()>mobileCard.getRealFlow()){
               remainFlow = Cardpack.getFlow()-mobileCard.getRealFlow();
           }else {
               remainFlow=0;
           }
           meg .append("剩余流量为："+remainFlow+"GB");
        }
        if(mobileCard.getSerPackage()instanceof SuperPackage){

            SuperPackage superPackage=(SuperPackage)pack;
            remainTalkTime=superPackage.getTalktime()>mobileCard.getRealTakTime()?superPackage.getTalktime()
                    -mobileCard.getRealTakTime():0;
            meg.append("通话时长:"+remainTalkTime+"分钟\n");
            remainSmsCount=superPackage.getSmsCount()>mobileCard.getRealSMSCount()?superPackage.getSmsCount()
                    -mobileCard.getRealSMSCount():0;
            meg.append("短信条数:"+remainSmsCount+"条\n");
            remainFlow=superPackage.getFlow()>mobileCard.getRealFlow()?superPackage.getFlow()
                    -mobileCard.getRealFlow():0;
            meg.append("上网流量:"+remainFlow+"GB");

        }
        System.out.println(meg);
    }
    //打印本月账单情况


    public void printAmountDetail(String number){
        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter("C:\\Users\\asus\\Desktop\\sc190701\\学员\\林锋鹏\\Java\\DD5G\\src\\账单消费记录.txt");
            Set numbers = cards.keySet();
            Iterator<String> it = numbers.iterator();
            List<ConsumInfo> infos;
            infos = consumInfo.get(number);
            //存储指定卡的所有消费记录
            //现有消费列表中是否存在此卡号的消费记录，是：true 否：false
            boolean isExist = false;
            while(it.hasNext()){
                String numberKey = it.next();
                if(consumInfo.containsKey(number)){
                    isExist = true;
                }/*else{   //如果
                    isExist = false;
                }*/

            }
            if(isExist){
                StringBuffer content = new StringBuffer("***********" + number + "消费记录************\n");
                content.append("序号\t类型\t数据（通话（分钟）/上网（MB）/短信（条））\t消费时间\n");
                for(int i = 0; i < infos.size(); i++){
                    ConsumInfo info = infos.get(i);
                    System.out.println((i + 1) + ".\t" + info.getType() + "\t" + info.getConsumData() +"\t\t"+ data.getDatas()+"\n");
                    content.append((i + 1) + ".\t" + info.getType() + "\t" + info.getConsumData() +"\t\t"+data.getDatas()+ "\n");
                }
                fileWriter.write(content.toString());
                fileWriter.flush();
                System.out.println("消息记录打印完毕！");
            }else{
                System.out.println("对不起，不存在此号码的消费记录，不能够打印！");
            }
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void chargingPack(String numbers){
        System.out.print("1.话痨套餐\t2.网虫套餐\t3.超人套餐(输入序号：)");
        ServicePackage newService = creatServicePackage();
        if(newService instanceof TalkPackage ){
            if(cards.get(numbers).getSerPackage()instanceof TalkPackage){
                System.out.println("你已是该套餐用户，无需更换");
            }else {
                cards.get(numbers).setSerPackage(newService);
                System.out.println("更改成功！");
            }
        }
        if(newService instanceof SuperPackage ){
            if(cards.get(numbers).getSerPackage()instanceof SuperPackage){
                System.out.println("你已是该套餐用户，无需更换");
            }else {
               cards.get(numbers).setSerPackage(newService);
                System.out.println("更改成功！");
            }
        }
        if(newService instanceof  NetPackage){
            if(cards.get(numbers).getSerPackage()instanceof NetPackage){
                System.out.println("你已是该套餐用户，无需更换");
            }else {
                cards.get(numbers).setSerPackage(newService );
                System.out.println("更改成功！");
            }
        }


    }

    //退网

    public void delCard(String numbers){
        if(cards.containsKey(numbers)){
            cards.remove(numbers);
            System.out.println("号码为"+numbers+"退网成功");
        }else {
            System.out.println("该号码不存在！");
        }
    }



    public void showDescription(){
        try  {
            File file = new File("C:\\Users\\asus\\Desktop\\sc190701\\学员\\林锋鹏\\Java\\DD5G\\src\\资费说明.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String line =null;
            while ((line=br.readLine())!=null){
                System.out.println(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public ServicePackage creatServicePackage() {
        ServicePackage service = null;
       aa: while (true) {
            int choosePackage = input.nextInt();

            switch (choosePackage) {
                case 1:
                    service = new TalkPackage();
                    break aa;
                case 3:
                    service = new SuperPackage();
                    break aa;
                case 2:
                    service = new NetPackage();
                    break aa;
                default:
                    System.out.println("输入错误，请重新输入：");
            }
            return service;
        }
        return  service;
    }

}
