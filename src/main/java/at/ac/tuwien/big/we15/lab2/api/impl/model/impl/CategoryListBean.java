package at.ac.tuwien.big.we15.lab2.api.impl.model.impl;

import at.ac.tuwien.big.we15.lab2.api.Category;
import at.ac.tuwien.big.we15.lab2.api.SelectableQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raimund on 26.04.2015.
 */
public class CategoryListBean {
    private List<CategoryBean> categories;

    public CategoryListBean() {
        this.categories = new ArrayList<CategoryBean>();
    }

    public CategoryListBean(List<CategoryBean> categories) {
        this.categories = categories;
    }

    public List<CategoryBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryBean> categories) {
        this.categories = categories;
    }
}
