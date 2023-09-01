package com.dayone;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DividendApplication {

	public static void main(String[] args) {

		SpringApplication.run(DividendApplication.class, args);

		try {
			Connection connection = Jsoup.connect("https://finance.yahoo.com/quote/COKE/history?period1=99100800&period2=1693440000&interval=1mo&filter=history&frequency=1mo&includeAdjustedClose=true");
			Document document= connection.get();

			Elements eles = document.getElementsByAttributeValue("data-test", "historical-prices");
			Element ele = eles.get(0);

			Element tbody = ele.children().get(1);// table > thead, tbody, tfoot 중 tbody 가져오기
			for (Element e : tbody.children()) { //tbody.childeren()은 tr, td 태그 빼고 그 안에 있는 span태그도 제외하고 오직 strong 태그에만 담겨있는 것이 Element로 나온다.
				String txt = e.text();
				if (!txt.endsWith("Dividend")) {
					continue;
				}
				String[] splits = txt.split(" ");
				String month = splits[0];
				int day = Integer.valueOf(splits[1].replace(",", ""));
				int year = Integer.valueOf(splits[2]);
				String dividend = splits[3];

				System.out.println(year +"/" + month +"/" +day + "/" + dividend);
				//System.out.println(txt);
			}
			//System.out.println(ele);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
