#include<iostream>  
using namespace std;   
  
void fast_sort(int *ptr, int begin, int end);  
  
int main()  
{  
    int array[10] = {11, 25, 11, 4, 88, 2, 108, 3, 2, 21};  
    fast_sort(array, 0, 9);  
  
    //输出显示代码  
    for(int i = 0; i < 10; i++)  
    {  
        cout << array[i] << "  ";  
    }  
    cout<<endl;  
    return 0;  
}  
  
void fast_sort(int *ptr, int begin, int end)  
{  
    int temp = *(ptr + begin);//设置初始比较基准数据  
    int i = begin + 1, j = end, curPosition = begin;//定义开头和结尾的I j  
    bool direction = false;  
    while(i <= j)  
    {  
        if(direction)  
        {  
            if(*(ptr + i) < temp)//如果当前数据小于基准数据 那么换位置 改当前位置  
            {  
                *(ptr + curPosition) = *(ptr + i);  
                curPosition = i;  
                direction = false;  
            }  
            i++;  
        }else//先从后到前比较数据  
        {  
            if(*(ptr + j) > temp)//如果最后一个大于基准 那么最后一个数据赋值给当前基准数据的那个位置 调整基准数据的位置  
            {  
                *(ptr + curPosition) = *(ptr + j);//  
                curPosition = j;  
                direction = true;  
            }  
            j--;  
        }  
    }       
  
    *(ptr + curPosition) = temp;     
    if(curPosition - begin > 1)//前面小的比较  
        fast_sort(ptr, begin, curPosition - 1);  
    if(end - curPosition > 1)//后面大的比较  
        fast_sort(ptr, curPosition + 1, end);   
}