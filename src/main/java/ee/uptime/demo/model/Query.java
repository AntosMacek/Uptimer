package ee.uptime.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Query {

    private final String query;
    private final String category;
    private final int page;


    private static final List<String> CATEGORIES = populateCategories();

    public Query(String query, String category, int page) {
        this.query = query;
        this.category = category;
        this.page = page;
    }

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


    public List<String> getCategoryList() {
        return CATEGORIES;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Query query1 = (Query) o;

        if (query != null ? !query.equals(query1.query) : query1.query != null) return false;
        return category != null ? category.equals(query1.category) : query1.category == null;

    }

    @Override
    public int hashCode() {
        int result = query != null ? query.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
