package ee.uptime.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Query {

    private String query;
    private String category;

    private static final List<String> CATEGORIES = populateCategories();

    private static List<String> populateCategories() {
        List<String> categoryList = new ArrayList<>();
        categoryList.add("Apparel");
        categoryList.add("Baby");
        categoryList.add("Beauty");
        categoryList.add("Books");
        categoryList.add("Classical");
        categoryList.add("DVD");
        categoryList.add("Electronics");
        categoryList.add("Garden");
        categoryList.add("GourmetFood");
        categoryList.add("HealthPersonalCare");
        categoryList.add("Jewelry");
        categoryList.add("Kitchen");
        categoryList.add("Magazines");
        categoryList.add("Marketplace");
        categoryList.add("Miscellaneous");
        categoryList.add("Music");
        categoryList.add("MusicalInstruments");
        categoryList.add("OfficeProducts");
        categoryList.add("PCHardware");
        categoryList.add("Photo");
        categoryList.add("Software");
        categoryList.add("SportingGoods");
        categoryList.add("Tools");
        categoryList.add("Toys");
        categoryList.add("VHS");
        categoryList.add("Video");
        categoryList.add("VideoGames");
        categoryList.add("Wireless");
        categoryList.add("WirelessAccessories");
        return categoryList;
    }


    public String getCategory() {
        return category;
    }


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getCategoryList() {
        return CATEGORIES;
    }


}
