package Item;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ItemFactoryTest {

    @Test
    public void knapSackTest() {
        //input
        Item item1 = Item.builder()
                .value(0.5)
                .weight(2.4)
                .id(1L)
                .build();

        Item item2 = Item.builder()
                .value(0.5)
                .weight(1.5)
                .id(2L)
                .build();

        Item item3 = Item.builder()
                .value(1.2)
                .weight(0.6)
                .id(3L)
                .build();

        List<Item> inputItemList = new ArrayList<>();
        inputItemList.add(item1);
        inputItemList.add(item2);
        inputItemList.add(item3);

        Solution inputSolution = Solution.builder()
                .value(0)
                .capacity(1)
                .itemList(new ArrayList<>())
                .build();

        //output expected
        List<Item> itemList1 = new ArrayList<>();
        itemList1.add(item3);

        Solution expectedSolution = Solution.builder()
                .value(1.2)
                .capacity(0.4)
                .itemList(itemList1)
                .build();

        Solution resultSolution = ItemFactory.knapSack(inputItemList, inputSolution, inputItemList.size() - 1);
        Set<Item> expectedItemSet = new HashSet<Item>(expectedSolution.getItemList());
        Set<Item> resultItemSet = new HashSet<Item>(resultSolution.getItemList());

        assertThat(expectedItemSet).isEqualTo(resultItemSet);
        assertThat(resultSolution.getCapacity()).isEqualTo(expectedSolution.getCapacity());
        assertThat(resultSolution.getValue()).isEqualTo(expectedSolution.getValue());
    }

    @Test
    public void knapSackTwoTest() {
        //input
        Item item1 = Item.builder()
                .value(0.5)
                .weight(2.4)
                .id(1L)
                .build();

        Item item2 = Item.builder()
                .value(0.5)
                .weight(1.5)
                .id(2L)
                .build();

        Item item3 = Item.builder()
                .value(1.2)
                .weight(0.6)
                .id(3L)
                .build();

        Item item4 = Item.builder()
                .value(0.5)
                .weight(0.5)
                .id(4L)
                .build();

        Item item5 = Item.builder()
                .value(0.4)
                .weight(0.5)
                .id(5L)
                .build();

        List<Item> inputItemList = new ArrayList<>();
        inputItemList.add(item1);
        inputItemList.add(item2);
        inputItemList.add(item3);
        inputItemList.add(item4);
        inputItemList.add(item5);

        Solution inputSolution = Solution.builder()
                .value(0)
                .capacity(1.1)
                .itemList(new ArrayList<>())
                .build();

        //output expected
        List<Item> itemList1 = new ArrayList<>();
        itemList1.add(item3);
        itemList1.add(item4);

        Solution expectedSolution = Solution.builder()
                .value(1.7)
                .capacity(0)
                .itemList(itemList1)
                .build();

        Solution resultSolution = ItemFactory.knapSack(inputItemList, inputSolution, inputItemList.size() - 1);
        Set<Item> expectedItemSet = new HashSet<Item>(expectedSolution.getItemList());
        Set<Item> resultItemSet = new HashSet<Item>(resultSolution.getItemList());

        assertThat(expectedItemSet).isEqualTo(resultItemSet);
        assertThat(resultSolution.getCapacity()).isEqualTo(expectedSolution.getCapacity());
        assertThat(resultSolution.getValue()).isEqualTo(expectedSolution.getValue());
    }

}