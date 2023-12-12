package uz.pdp;

import uz.pdp.entity.Country;
import uz.pdp.entity.Region;
import uz.pdp.service.CountryImpl;

import java.io.IOException;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) throws IOException {

        Country country = new Country(12,"oo","russia","db/true",
                LocalDate.of(2004,2,12),12313414l,
                new Region(3,"Ota","Uzb"));
        Boolean serialize = CountryImpl.getInstance().logger();
        System.out.println(serialize);

    }
}
