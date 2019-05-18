package book;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BookFinal {
    @Test
    public void getBookName() throws IOException {


        Document document = Jsoup.connect("http://www.shicimingju.com/book").get();
        Elements e = document.select(".bookmark-list h2");


        for (Element element : e) {
            Elements a = element.select("a");

            // 获取书籍英文名称目录
            String str = a.attr("href");
            str = str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf("."));

            //System.out.println(str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf(".")));

            //System.out.print(a.text()+" : ");System.out.println(a.attr("abs:href"));
            String bookName = a.text();
            bookchapter(str,bookName);

        }
    }
    public void bookchapter(String str,String bookName) throws IOException{
        String contain= "";
        Document document = Jsoup.connect("http://www.shicimingju.com/book/"+str+".html").get();
        Elements elements = document.select(".book-mulu li");

        for (Element element : elements) {
            Elements a = element.select("a");
            String urlPath = a.attr("href");
//            System.out.println(a.attr("href"));
            contain = contain + chapter(urlPath,bookName);
        }
        saveText(contain, bookName);


    }
    public String chapter(String urlPath,String bookName) throws IOException {

        String data = "";
        Document document = Jsoup.connect("http://www.shicimingju.com"+urlPath).get();
        Elements elements = document.getElementsByTag("p");
        Elements b = document.getElementsByTag("h1");


        for (Element element : elements) {

            element.text();
            data = data + b.text() + element.text() + "\n\n\n\n\n\n";
        }

        return data;




    }

    public void saveText(String data, String bookName) {
        String path = "E:/novel/" + bookName + "/";//文件夹路径
        File dirName = new File(path);
        if (!dirName.exists()) {
            dirName.mkdirs();
            System.out.println(dirName + "目录创建成功");
        }
        try {
            FileWriter writer = new FileWriter(path + bookName + ".txt");
            writer.write(data);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}