import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static Document getPage() throws IOException {
        File file = new File("");
        String url = "https://bel-pol.pl/porta/drzwi-wewnetrzne,g11.html?order=cena,asc";
        Document doc = Jsoup.connect(url).get();//get values

        return doc;
    }

    static class Product {
        String url;
        String name;
        String price;

        public Product(String url, String name, String price) {
            this.url = url;
            this.name = name;
            this.price = price;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "url='" + url + '\'' +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        Document doc = getPage();
        List<Product> elementList = new ArrayList<>();

        Elements elements = doc.getElementsByAttributeValue("class", "offer_box");

        //variant 1
        for (Element element : elements) {
            String url = element.child(1).child(0).child(0).attr("href");
            String name = element.child(1).child(0).child(0).text();
            Elements prices = element.getElementsByAttributeValue("class", "nowrap");
            //variant 2
            for (Element price : prices) {
                String allGoldWorld = price.text();
                elementList.add(new Product(url, name, allGoldWorld));
            }
        }
    }
}