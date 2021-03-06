//package com.example.find_dog.util;
//
//import com.example.find_dog.model.entity.Adoption;
//import com.example.find_dog.repository.AdoptionRepository;
//import lombok.RequiredArgsConstructor;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//
//@RequiredArgsConstructor
//public class Crawler {
//    private final AdoptionRepository adoptionRepository;
//
//    @Bean
//    public ApplicationRunner applicationRunner() {
//        return args -> {
//            String SEARCH_URL = "https://dmanimal.co.kr/adoption?keyword_type=all&keyword=";
//            String[] types = {"사모예드", "빠삐용", "시바견", "퍼그", "진돗개", "푸들", "페키니즈", "차우차우", "브라도리트리버", "보더콜리",
//                    "웰시코기", "잭러셀테리어", "비숑프리제", "프렌치불독", "시베리안허스키", "골든리트리버"};
//            String currType;
//            Elements elements;
//            String detailUrl;
//            for (int i = 0; i < types.length; i++) {
//                currType = types[i];
//                String typeUrl = SEARCH_URL + types[i];
////            System.out.println(typeUrl);
//
//                Document doc = Jsoup.connect(typeUrl).get();
//                elements = doc.select(".post_link_wrap");
//                System.out.println(types[i] + " 상세조회 페이지 갯수: " + elements.size());
//
//                for (Element element : elements) {
//                    String image = element.select(".card_wrapper").attr("style");
//                    image = image.substring(image.lastIndexOf("(") + 1);
//                    int idx = image.indexOf(")");
//                    image = image.substring(0, idx);    // 썸네일 이미지
////                System.out.println("image: " + image);
//
//                    detailUrl = "https://dmanimal.co.kr/" + element.attr("href");
////                System.out.println(detailUrl);
//
//                    doc = Jsoup.connect(detailUrl).get();
//                    elements = doc.select(".board_txt_area");
////                System.out.println(elements);
//
////                String type = elements.select("tr:nth-child(1) > td:nth-child(2) > div > span").text();         // 견종 (크롤링으로 수집한 데이터로 저장)
//                    String type = currType;                                                                         // 견종 (현재 검색어로 저장)
//                    String age = elements.select("tr:nth-child(1) > td:nth-child(4) > div > span").text() ;          // 나이
//                    String gender = elements.select("tr:nth-child(2) > td:nth-child(2) > div > span").text();       // 성별
//                    String note = elements.select("tr:nth-child(2) > td:nth-child(4) > div > span").text();         // 특이사항
//                    String vaccine = elements.select("tr:nth-child(3) > td:nth-child(2) > div > span").text();      // 접종유무
//                    String neuter = elements.select("tr:nth-child(3) > td:nth-child(4) > div > span").text();       // 중성화유무
//                    String reason = elements.select("tr:nth-child(4) > td:nth-child(2) > div").text();              // 보호소로 오게 된 이유
//                    String fave = elements.select("tr:nth-child(5) > td:nth-child(2) > div").text();                // 좋아하는 것
//                    String dislike = elements.select("tr:nth-child(5) > td:nth-child(4) > div").text();             // 싫어하는 것
//
////                System.out.println("type: " + type + "/ age: " + age + "/ gender: " + gender + "/ note: " + note + "/ vaccine: " + vaccine + "/ neuter: " + neuter +
////                        "/ reason: " + reason + "/ before: " + before + "/ like: " + fave + "/ dislike: " + dislike + "/ image: " + image);
//                    Adoption tmp = new Adoption(type, age, gender, note, vaccine, neuter, reason, fave, dislike, image);
//                    tmp.check_valide();
//                    adoptionRepository.save(tmp);
//
////                    break;
//                }
////                break;
//            }
//
//        };
//    }
//}
