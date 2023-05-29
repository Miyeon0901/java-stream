package com.fastcampus.doitmyself.chapter10;

import com.fastcampus.doitmyself.chapter10.model.Price;
import com.fastcampus.doitmyself.chapter10.service.BasicPriceProcessor;
import com.fastcampus.doitmyself.chapter10.service.DiscountPriceProcessor;
import com.fastcampus.doitmyself.chapter10.service.PriceProcessor;
import com.fastcampus.doitmyself.chapter10.service.TaxPriceProcessor;

public class Chapter10Section3 {
    /**
     * Decorator Pattern
     * - 구조 패턴의 하나
     * - 용도에 따라 객체에 기능을 계속 추가(decorate)할 수 있게 해준다.
     */
    public static void main(String[] args) {
        Price unprocessedPrice = new Price("Original Price");

        PriceProcessor basicPriceProcessor = new BasicPriceProcessor();
        PriceProcessor discountPriceProcessor = new DiscountPriceProcessor();
        PriceProcessor taxPriceProcessor = new TaxPriceProcessor();

        PriceProcessor decoratedPriceProcessor = basicPriceProcessor
                .andThen(discountPriceProcessor)
                .andThen(taxPriceProcessor);

        Price processedPrice = decoratedPriceProcessor.process(unprocessedPrice);
        System.out.println(processedPrice.getPrice());

        PriceProcessor decoratedPriceProcessor2 = basicPriceProcessor
                .andThen(taxPriceProcessor)
                // PriceProcessor가 functional interface이므로 lambda를 통해 생성 가능 - 재활용 불가
                .andThen(price -> new Price(price.getPrice() + ", then apply another procedure")); 

        Price processedPrice2 = decoratedPriceProcessor2.process(unprocessedPrice);
        System.out.println(processedPrice2.getPrice());
    }
}
