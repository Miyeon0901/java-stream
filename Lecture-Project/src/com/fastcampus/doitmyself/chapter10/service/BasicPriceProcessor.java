package com.fastcampus.doitmyself.chapter10.service;

import com.fastcampus.doitmyself.chapter10.model.Price;

public class BasicPriceProcessor implements PriceProcessor{


    @Override
    public Price process(Price price) {
        return price;
    }
}
