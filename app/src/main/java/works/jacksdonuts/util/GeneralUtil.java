package works.jacksdonuts.util;

import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.List;

import works.jacksdonuts.core.initialdata.Price;

/**
 * Created by siddharth on 6/27/18.
 */

public class GeneralUtil {

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Price getPrice(List<Price> priceList) {
        Price finalPriceObject = new Price();
        for(Price price : priceList) {
            if (price.get20oz() != null) {
                finalPriceObject.set20oz(price.get20oz());
            }
            if (price.get16oz() != null) {
                finalPriceObject.set16oz(price.get16oz());
            }
            if (price.get20oz() != null) {
                finalPriceObject.set20oz(price.get20oz());
            }
            if (price.getEach() != null) {
                finalPriceObject.setEach(price.getEach());
            }
            if (price.getDozen() != null) {
                finalPriceObject.setDozen(price.getDozen());
            }
        }
        return finalPriceObject;
    }
}
