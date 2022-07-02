package demo01_xml解析;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class Test02_xPath {
    /*
        DOM4j+xPath解析xml使用步骤：
            1.导入dom4j和xpath的jar包
            2.创建解析器 SaxReader
            3.加载xml文档得到Document对象  read()
            4.定义xpath表达式解析Document对象的API选取节点
                Node selectSingleNode("xpath语法");   获得一个节点(标签,元素)
                List selectNodes("xpath语法");        获得多个节点(标签,元素)
     */

    //绝对路径
    @Test
    public void  test_绝对路径() throws DocumentException {
        //绝对路径：以/开头 第一个/后面内容表示的就是xml文档的根标签名称
        //需求：获取深圳的最低温度

        //2.创建解析器 SaxReader
        SAXReader saxReader = new SAXReader();

        //3.加载xml文档得到Document对象  read()
        Document document = saxReader.read("src//tianqi.xml");

        //4.定义xpath表达式解析Document对象的API选取节点
        String xpath= "/天气预报/深圳/温度/最低温度";

        //Node包含的有元素 属性 文本  Element是Node的子类
        Node node = document.selectSingleNode(xpath);
        System.out.println("深圳的最低温度 = " + node.getText());
    }

    //相对路径
    @Test
    public void  test_相对路径() throws DocumentException {
        //相对路径：相对深圳的最低温度节点 找到广州的最低温度节点
        // 以../开头  ../表示上一级目录  ./表示当前目录
        //需求：获取广州的最低温度【相对路径写法  相对设置的最低温度】

        //2.创建解析器 SaxReader
        SAXReader saxReader = new SAXReader();

        //3.加载xml文档得到Document对象  read()
        Document document = saxReader.read("src//tianqi.xml");

        //4.定义xpath表达式解析Document对象的API选取节点
        String xpath= "/天气预报/深圳/温度/最低温度";           //绝对路径

        //Node包含的有元素 属性 文本  Element是Node的子类
        Node node = document.selectSingleNode(xpath);   //深圳最低温度节点
        System.out.println("深圳的最低温度 = " + node.getText());


        String xpath02= "../../../广州/温度/最低温度";          //相对路径
        Node node02 = node.selectSingleNode(xpath02);
        System.out.println("广州最低温度 = " + node02.getText());

    }

    //全文搜索
    @Test
    public void  test_全文搜索() throws DocumentException {
        //需求：获取所有城市的最高温度  xpath表达式： //最高温度

        //2.创建解析器 SaxReader
        SAXReader saxReader = new SAXReader();

        //3.加载xml文档得到Document对象  read()
        Document document = saxReader.read("src//tianqi.xml");

        //4.定义xpath表达式解析Document对象的API选取节点
        String xpath= "//最高温度";           //全文搜索路径

        List<Node> list = document.selectNodes(xpath);
        for (Node node : list) {
            System.out.println(node.getName() + " " + node.getText());
        }
    }

    //条件搜索
    @Test
    public void  test_条件搜索() throws DocumentException {
        //需求：获取level属性为C的最高温度  xpath表达式： //最高温度[@level='C']

        //2.创建解析器 SaxReader
        SAXReader saxReader = new SAXReader();

        //3.加载xml文档得到Document对象  read()
        Document document = saxReader.read("src//tianqi.xml");

        //4.定义xpath表达式解析Document对象的API选取节点
        String xpath= "//最高温度[@level='C']";           //条件搜索路径

        List<Node> list = document.selectNodes(xpath);
        for (Node node : list) {
            System.out.println(node.getName() + " " + node.getText());
        }

    }
}
