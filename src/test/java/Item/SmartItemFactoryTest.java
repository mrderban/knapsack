package Item;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SmartItemFactoryTest {

    @Test
    public void brickSortingTest() {
        //input
        Item item1 = Item.builder()
                .value(0.5)
                .id(1L)
                .build();
        Item item2 = Item.builder()
                .value(0.5)
                .id(2L)
                .build();

        Item item3 = Item.builder()
                .value(1)
                .id(3L)
                .build();

        Item item4 = Item.builder()
                .value(0.8)
                .id(4L)
                .build();

        Item item5 = Item.builder()
                .value(0.2)
                .id(5L)
                .build();

        List<Item> inputItemList = new ArrayList<>();
        inputItemList.add(item1);
        inputItemList.add(item2);
        inputItemList.add(item3);
        inputItemList.add(item4);
        inputItemList.add(item5);

        //before sorting
        inputItemList.sort(Item::compareTo);

        List<Item> sortedItemList = new ArrayList<>();
        sortedItemList.add(item5);
        sortedItemList.add(item1);
        sortedItemList.add(item2);
        sortedItemList.add(item4);
        sortedItemList.add(item3);

        assertThat(inputItemList).isEqualTo(sortedItemList);
    }

    @Test
    public void SmartKnapSackTest() {
        //input
        Item item1 = Item.builder()
                .value(0.8)
                .weight(0.5)
                .id(1L)
                .build();

        Item item2 = Item.builder()
                .value(0.8)
                .weight(1.5)
                .id(2L)
                .build();

        Item item3 = Item.builder()
                .value(1.2)
                .weight(0.5)
                .id(3L)
                .build();

        List<Item> inputItemList = new ArrayList<>();
        inputItemList.add(item1);
        inputItemList.add(item2);
        inputItemList.add(item3);

        SmartItemFactory.smartKnapSack(inputItemList, 2.0, 0.1);

    }

    @Test
    public void SmartKnapSackTestTwo() {
        //input
        Item item1 = Item.builder()
                .value(4)
                .weight(1)
                .id(1L)
                .build();

        Item item2 = Item.builder()
                .value(40)
                .weight(4)
                .id(2L)
                .build();

        Item item3 = Item.builder()
                .value(30)
                .weight(8)
                .id(3L)
                .build();

        Item item4 = Item.builder()
                .value(6)
                .weight(2)
                .id(4L)
                .build();

        Item item5 = Item.builder()
                .value(10)
                .weight(1)
                .id(5L)
                .build();

        Item item6 = Item.builder()
                .value(7)
                .weight(5)
                .id(6L)
                .build();

        Item item7 = Item.builder()
                .value(70)
                .weight(2)
                .id(7L)
                .build();

        List<Item> inputItemList = new ArrayList<>();
        inputItemList.add(item1);
        inputItemList.add(item2);
        inputItemList.add(item3);
        inputItemList.add(item4);
        inputItemList.add(item5);
        inputItemList.add(item6);
        inputItemList.add(item7);

        SmartItemFactory.smartKnapSack(inputItemList, 8, 1);

    }

}