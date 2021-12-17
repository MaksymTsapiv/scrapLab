package scraper;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DefaultScraper implements Scraper {
    private static final String PRICE_SELECTOR = ".detail__info-xlrg";
    private static final String BEDS_SELECTOR = ".nhs_BedsInfo";

    @Override @SneakyThrows
    public Home scrape(String url) {
        Document doc = Jsoup.connect(url).get();
        System.out.println(doc);
        int price = getPrice(doc);
        int beds = getBeds(doc);
        return new Home(price, beds, 0, 0);
    }

    private int getPrice(Document doc) {
        String price = doc.select(PRICE_SELECTOR).text().replaceAll("[^0-9]", "");
        return Integer.parseInt(price);
    }

    private int getBeds(Document doc) {
        String beds = doc.select(BEDS_SELECTOR).text().replaceAll("[^0-9]", "");
        return Integer.parseInt(beds);
    }

}
