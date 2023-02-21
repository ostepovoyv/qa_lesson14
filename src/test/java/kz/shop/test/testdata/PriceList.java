package kz.shop.test.testdata;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PriceList {

    private String fileName;
    private int sheet;
    private int row;
    private int cell;
    private String value;

}
