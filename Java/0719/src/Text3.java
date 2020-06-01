/**
 * @author asus
 * @date 2019/7/19 20:03
 */
public class Text3 {
    public static void main(String[] args) {
        int[] array = {42, 20, 17, 14};

        quickSort(array,1,2);
        System.out.println(array);
    }
    public static void quickSort(int a[],int l,int r) {
        if (l >= r)
        { return;}

        int i = l;
        int j = r;
        int key = a[l];
        //选择第一个数为key
        while (i < j) {

            while (i < j && a[j] >= key)
            //从右向左找第一个小于key的值
            {     j--;}
            if (i < j) {
                a[i] = a[j];
                i++;
            }
                //从左向右找第一个大于key的值
            while (i < j && a[i] < key)
            {   i++;}

            if (i < j) {
                a[j] = a[i];
                j--;
            }
        }
        //i == j
        a[i] = key;
        quickSort(a, l, i - 1);//递归调用
        quickSort(a, i + 1, r);//递归调用


    }




//        int temp;
//        int lenth = array.length;
//
//        for(int i=0;i<lenth-1;i++){
//            for(int j=i+1;j>0;j--){
//                if(array[j] < array[j-1]){
//                    temp = array[j-1];
//                    array[j-1] = array[j];
//                    array[j] = temp;
//                    System.out.println(array[j]);
//                }else{         //不需要交换
//                    break;
//                }
//            }
//        }
//        System.out.println(array);
//        int temp;//临时变量
//        for (int i = 0; i < arr.length - 1; i++) {
//            //表示趟数，一共arr.length-1次。
//            for (int j = arr.length - 1; j > i; j--) {
//                if (arr[j] < arr[j - 1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j - 1];
//                    arr[j - 1] = temp;
//                }
//            }
//        }  冒泡排序！！
}


//    public static void select_sort(int array[], int lenth) {
//
//        for (int i = 0; i < lenth - 1; i++) {
//
//            int minIndex = i;
//            for (int j = i + 1; j < lenth; j++) {
//                if (array[j] < array[minIndex]) {
//                    minIndex = j;
//                }
//            }
//            if (minIndex != i) {
//                int temp = array[i];
//                array[i] = array[minIndex];
//                array[minIndex] = temp;
//            }
//        }
//    }

