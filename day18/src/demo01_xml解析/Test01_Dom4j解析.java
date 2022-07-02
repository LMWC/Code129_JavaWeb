package demo01_xml解析;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

public class Test01_Dom4j解析 {
    public static void main(String[] args) throws Exception {
        /*
            DOM4j解析步骤：
                1.导入dom4j jar包
                2.创建解析器                         SAXReader sr = new SAXReader();
                3.通过解析器加载XML文件得到Document对象 Document document = sr.read("day18_code\\src\\books.xml");
                4.获取xml文档根节点                   document.getRootElement();
                5.根据标签的层级关系获取指定标签的内容
            常用方法：
                List elements():获取当前标签下的所有子标签元素对象
                String getName()：获取当前标签元素的名称
                String getText()：获取当前标签体内的文本值
                String attributeValue(String attrName)：获取当前标签指定属性的值
                Element element(String name)：获取该标签元素下指定名称的子标签元素
                String elementText(String name)：获取该标签元素下指定名称的子标签元素内的文本值

         */
        //2.创建解析器
        SAXReader sr = new SAXReader();

        //3.通过解析器加载XML文件得到Document对象
        Document document = sr.read("day18_code\\src\\books.xml");

        //4.获取xml文档根节点
        Element root = document.getRootElement();
        System.out.println("根标签名称 = " + root.getName());

        //5.根据标签的层级关系获取指定标签的内容
        List<Element> elements = root.elements();

        //遍历根标签下的子标签集合
        for (Element element : elements) {
            System.out.print("\t element = " + element.getName());
            System.out.println(" id属性值 = "+element.attributeValue("id"));

            //方式三：获取book标签下的子标签内容
            String name = element.elementText("name");
            String author = element.elementText("author");
            String sale = element.elementText("sale");
            System.out.println("\t\t"+name +" "+author+" "+sale);

            //方式二：获取book标签下的子标签内容
            /*
            Element nameEle = element.element("name");
            Element authorEle = element.element("author");
            Element saleEle = element.element("sale");
            System.out.println("\t\t"+nameEle.getName() +" "+ nameEle.getText());
            System.out.println("\t\t"+authorEle.getName() +" "+ authorEle.getText());
            System.out.println("\t\t"+saleEle.getName() +" "+ saleEle.getText());
            */


            //方式一：获取book标签下的子标签内容
            /*
            //获取book标签下的子标签
            List<Element> childs = element.elements();
            //遍历book标签下的子标签
            for (Element child : childs) {
                System.out.println("\t\t"+child.getName() +" "+ child.getText());
            }
            */
        }
    }
}
