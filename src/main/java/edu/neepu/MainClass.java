package edu.neepu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainClass {

//
//    @Test
//    public void test1() throws IOException {
//        System.out.println("!!!");
//        Document document = Jsoup.connect("http://www.baidu.com").get();
//        System.out.println(document);
//    }
//    @Test
//    public void test2() {
//        String html = "<html><head><title>网页标题</title></head>" +
//                "<body><p>一段文字</p></body></html>";
//        Document document = Jsoup.parse(html);
//        System.out.println(document.title());
//    }
//    @Test
//    public void test3() throws IOException {
//        File file = new File("C:\\Users\\lzx\\IdeaProjects\\MDemo\\" +
//                "src\\main\\resources\\main.html");
//        Document document = Jsoup.parse(file, "UTF-8");
//        System.out.println(document.title());
//        Elements elements = document.getElementsByTag("p");
//        System.out.println(elements);
//        for (Element element : elements) {
//            System.out.println(element.text());
//        }
//        //按id或class获取标签
////        Element p1 = document.getElementById("p1");
////        Elements c3 = document.getElementsByClass("c3");
////        Elements ps = document.getElementsByTag("p");
//
//    }
//    @Test
//    public void test4() throws IOException {
//        Document document = Jsoup.connect("https://www.csdn.net/").get();
//        //a
//        Elements elements = document.select("a[href]");
//        System.out.println(elements);
//        for (Element element : elements) {
//            System.out.println(element.attr("abs:href"));
//        }
//    }
//    @Test
//    public void test5() throws IOException {
//        //知乎答案简介
//        Document document = Jsoup.connect("https://www.zhihu.com/explore/recommendations").get();
//        //System.out.println(document);
//        Elements elements = document.select(".zh-summary");
//        for (Element element : elements) {
//            System.out.println(element.text());
//            System.out.println("    ");
//        }
//    }
//    @Test
//    public void test6() throws IOException {
//        //获取知乎推荐栏每一项回答
//        Document document = Jsoup.connect("https://www.zhihu.com/explore/recommendations").get();
//        //System.out.println(document);
//        Elements elements = document.select(".zm-item");
//        for (Element element : elements) {
//            //获得question_link
//            Element a = element.select(".question_link").first();
//            if(a!=null){
//                System.out.println("问题标题"+a.text());
//                System.out.println("回答连接"+a.attr("abs:href"));
//            }
//        }
//
//    }
//
//    @Test
//    public void test7() throws IOException {
//        for (int i =1;i<21;i++) {
//            Document document = Jsoup.connect("https://store.steampowered.com/search/?specials=1&page=" + i)
//                    .header("Accept-Language","zh-cn")
//                    .userAgent("Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; en) Presto/2.8.131 Version/11.11")
//                    .get();
//            Elements elements = document.select(".search_result_row");
//            for (Element element : elements) {
//                Element a = element.select(".title").first();
//                if (a != null) {
//                    System.out.println("游戏名:" + a.text());
//                }
//
//                Element b = element.select(".search_released").first();
//                if (b != null) {
//                    System.out.println("发布时间:" + b.text());
//                }
//                Element c = element.select(".search_review_summary").first();
//                if (c != null) {
//                    System.out.println("综合评价:" + c.attr("data-tooltip-html"));
//                }
//                Element d = element.select(".search_discount").first();
//                if (d != null) {
//                    System.out.println("打折力度:" + d.text());
//                }
//                Element e = element.select(".search_price").first();
//                if (e != null) {
//                    System.out.println("原价现价:" + e.text());
//                    System.out.println();
//                }
//            }
//        }
//
//    }
//
//    @Test
//    public void test8() throws IOException {
//        //在必应上爬图片
//        for(int i=1;i<=20;i++) {
//
//            Document document = Jsoup.connect("https://bing.ioliu.cn/?p="+ i).get();
//            Elements elements = document.select(".container");
//            Elements elements1 = document.select(".page");
//            System.out.println(elements1.text());
//            for (Element element : elements) {
//                //获取"question_link")
//                Elements d = element.select(".download");
//                System.out.println(d.attr("abs:href"));
//
//            }
//        }
//    }
//
//    @Test
//    public void test9() throws IOException {
//        //爬文档
//
//        Document document = Jsoup.connect("http://www.shicimingju.com/book/").get();
//        Elements elements = document.select(".bookmark-list");
//        System.out.println(elements.text());
//
//    }
//
//    @Test
//    public void test10() throws IOException {
//        for (int i = 1; i <121; i++) {
//            Document document = Jsoup.connect("http://www.shicimingju.com/book/sanguoyanyi/"+i+".html").get();
//            Elements elements = document.getElementsByTag("p");
//            for (Element element : elements) {
//
//
//                System.out.println(element.text());
//
//            }
//        }
//
//
//    }



    public static void downloadImage(String img_Url,String imgName) throws UnsupportedEncodingException {
        String filePath="C:\\Users\\lzx\\Desktop\\image";
        String fileName =  imgName;//图片名
        File file = new File(filePath+File.separator+fileName+".jpg");//创建文件
        try{

            URL url = new URL(img_Url);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();//URLConnection urlConnection =  url.openConnection();
            urlConnection.setConnectTimeout(10 * 1000);
            DataInputStream dataInputStream = new DataInputStream(urlConnection.getInputStream());//InputStream  inputStream = urlConnection.getInputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            byte b [] = new byte[4096];
            int size;
            while((size=dataInputStream.read(b))!=-1){
                dataOutputStream.write(b,0,size);
            }
            dataInputStream.close();
            dataOutputStream.close();
            System.out.println("下载图片："+fileName+"成功！");
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Test
    public  void test11(){//下载图片
        try{
            for(int i=1;i<=20;i++){
                System.out.println("第"+i+"页：");
                Document document = Jsoup.connect("https://bing.ioliu.cn/?p="+i).get();
                Elements elements = document.select(".card.progressive");//Elements elements = document.select("a.ctrl.download");
                for(Element element :elements){
                    System.out.println("下载地址： "+element.select("a.ctrl.download").attr("abs:href"));
                    String imgurl = element.select("a.ctrl.download").attr("abs:href").toString();
                    String imgname_e1 = element.select(".location em.t").text();//图片拍摄地点
                    String imgname_e2 = element.select(".calendar em.t").text();//图片拍摄的时间
                    System.out.println("图片拍摄地点："+imgname_e1+"   图片拍摄时间："+imgname_e2);
                    MainClass.downloadImage(imgurl,(imgname_e1+imgname_e2));
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }




}
