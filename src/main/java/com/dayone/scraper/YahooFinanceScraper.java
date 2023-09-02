package com.dayone.scraper;

import com.dayone.model.Company;
import com.dayone.model.Dividend;
import com.dayone.model.ScrapedResult;
import com.dayone.model.constants.Month;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class YahooFinanceScraper implements Scraper{
    // "https://finance.yahoo.com/quote/COKE/history?period1=99100800&period2=1693440000&interval=1mo&filter=history&frequency=1mo&includeAdjustedClose=true" 원 URL
    private static final String STATIC_URL = "https://finance.yahoo.com/quote/%s/history?period1=%d&period2=%d&interval=1mo";
    private static final String SUMMARY_URL = "https://finance.yahoo.com/quote/%s?p=%s";
    private static final long START_TIME = 86400; // 60 * 60 * 24

    @Override
    public ScrapedResult scrap(Company company) {

        var scrapedResult = new ScrapedResult();
        scrapedResult.setCompany(company);
        try {
            long now = System.currentTimeMillis() / 1000;
            String url = String.format(STATIC_URL, company.getTicker(), START_TIME, now);
            Connection connection = Jsoup.connect(url);
            Document document= connection.get();

            Elements parsingDivs = document.getElementsByAttributeValue("data-test", "historical-prices");
            Element tableEle= parsingDivs.get(0);

            Element tbody = tableEle.children().get(1);// table > thead, tbody, tfoot 중 tbody 가져오기

            List<Dividend> dividends = new ArrayList<>();

            for (Element e : tbody.children()) { //tbody.childeren()은 tr, td 태그 빼고 그 안에 있는 span태그도 제외하고 오직 strong 태그에만 담겨있는 것이 Element로 나온다.
                String txt = e.text();
                if (!txt.endsWith("Dividend")) {
                    continue;
                }
                String[] splits = txt.split(" ");
                int month = Month.strToNumber(splits[0]);
                int day = Integer.valueOf(splits[1].replace(",", ""));
                int year = Integer.valueOf(splits[2]);
                String dividend = splits[3];

                if (month < 0) {
                    throw new RuntimeException("Unexpected Month enum value -> " + splits[0]);
                }

                dividends.add(Dividend.builder()
                                .date(LocalDateTime.of(year, month, day, 0 , 0))
                                .dividend(dividend)
                                .build());

            }
            scrapedResult.setDividends(dividends);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return scrapedResult;
    }

    @Override
    public Company scrapCompanyByTicker(String ticker) {
        String url = String.format(SUMMARY_URL, ticker, ticker);

        try {
            Document document = Jsoup.connect(url).get();
            Element titleEle = document.getElementsByTag("h1").get(0);
            String title = titleEle.text().split(" - ")[1].trim();

            return Company.builder()
                    .ticker(ticker)
                    .name(title)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
