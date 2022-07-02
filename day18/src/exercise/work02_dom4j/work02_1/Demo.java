package exercise.work02_dom4j.work02_1;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws Exception{
        //（1）创建xml解析器对象
        SAXReader saxReader = new SAXReader();
        //（2）定位xml的位置
        InputStream is = new FileInputStream("day18\\src\\exercise\\work01_xml\\work01_1\\books.xml");//注意这里的文件定位，根据自己的文件位置来定义
        //（3）获取Document对象
        Document doc = saxReader.read(is);
        //（4）获取根元素
        Element root = doc.getRootElement();
        //（5）获取根元素下的所有book的元素
        List<Element> elements = root.elements();
        //（6）遍历所有的book元素
        if (elements!=null&&elements.size()!=0){//判断获取的子元素集合是否为空
            for (Element book : elements){
                //（7）获取元素中各种内容
                String bookName = book.elementText("bookName");//获取该元素的子元素所对应的元素的文本内容
                String press = book.elementText("press");
                String publicationDate = book.elementText("publicationDate");
                String price = book.elementText("price");
                //（8）输出内容
                System.out.println("书名:"+bookName+"，出版社:"+press+"，出版日期:"+publicationDate+"，售价:"+price);
            }
        }
    }
}
