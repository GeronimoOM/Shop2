package ukma.groupproject.shop.view;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.control.*;
import ukma.groupproject.shop.view.util.DefaultStringConverter;
import ukma.groupproject.shop.view.util.StringConverter;

import java.util.*;

public class AutoCompleteChoiceTextField<E> extends TextField {

    private static final int DEFAUL_RESULT_COUNT = 5;

    private ContextMenu entriesMenu;

    private StringConverter<E> converter;
    private Map<String, E> entryMap;
    private SortedSet entryStrings;

    private IntegerProperty resultCount;
    private ObjectProperty<E> selected;
    private ObservableList<E> entries;

    public AutoCompleteChoiceTextField() {
        super();
        entriesMenu = new ContextMenu();

        converter = new DefaultStringConverter<E>();
        entryMap = new HashMap<>();
        entryStrings = new TreeSet();

        resultCount = new SimpleIntegerProperty(DEFAUL_RESULT_COUNT);
        selected = new SimpleObjectProperty<>();
        entries = FXCollections.observableArrayList();

        entries.addListener(new ListChangeListener<E>() {
            @Override
            public void onChanged(Change<? extends E> c) {
                while(c.next()) {
                    if(c.wasAdded()) {
                        for (E entry : c.getAddedSubList()) {
                            String entryString = converter.convert(entry);
                            entryStrings.add(entryString);
                            entryMap.put(entryString, entry);
                        }
                    } else if(c.wasRemoved()) {
                        for (E entry : c.getRemoved()) {
                            String entryString = converter.convert(entry);
                            entryStrings.remove(entryString);
                            entryMap.remove(entryString);
                        }
                    }
                }
            }
        });

        textProperty().addListener((observableValue, oldVal, newVal) -> {
            selectedProperty().set(null);
            if (getText().isEmpty()) {
                entriesMenu.hide();
            } else {
                SortedSet<String> searchResult = entryStrings.subSet(getText(), getText() + Character.MAX_VALUE);
                if (!entries.isEmpty()) {
                    populateMenu(searchResult);
                    if (!entriesMenu.isShowing()) {
                        entriesMenu.show(AutoCompleteChoiceTextField.this, Side.BOTTOM, 0, 0);
                        entriesMenu.setPrefWidth(getWidth());
                    }
                } else {
                    entriesMenu.hide();
                }
            }
        });
        focusedProperty().addListener((observableValue, aBoolean, aBoolean2) -> entriesMenu.hide());
    }

    private void populateMenu(SortedSet<String> searchResult) {
        List<CustomMenuItem> menuItems = new LinkedList<>();
        int count = Math.min(searchResult.size(), resultCount.get());
        Iterator<String> resultsIter = searchResult.iterator();
        while(resultsIter.hasNext() && count > 0) {
            String result = resultsIter.next();
            CustomMenuItem item = new CustomMenuItem(new Label(result), true);
            item.setOnAction(actionEvent -> {
                setText(result);
                selected.set(entryMap.get(result));
                entriesMenu.hide();
            });
            menuItems.add(item);
        }
        entriesMenu.getItems().clear();
        entriesMenu.getItems().addAll(menuItems);
    }

    public StringConverter<E> getConverter() {
        return converter;
    }

    public void setConverter(StringConverter<E> converter) {
        this.converter = converter;
    }

    public IntegerProperty resultCountProperty() {
        return resultCount;
    }

    public int getResultCount() {
        return resultCount.get();
    }

    public void setResultCount(int resultCount) {
        this.resultCount.set(resultCount);
    }

    public ObjectProperty<E> selectedProperty() {
        return selected;
    }

    public E getSelected() {
        return selected.get();
    }

    public void setSelected(E selected) {
        this.selected.set(selected);
    }

    public ObservableList<E> getEntries() {
        return entries;
    }

}