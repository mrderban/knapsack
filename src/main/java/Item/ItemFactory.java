package Item;

import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.List;

public class ItemFactory {


    public static double roundToOneDecimal(double d) {
        return Precision.round(d, 2);
    }


    //returns the maximum value (in €) that can be put in a knapsack of capacity maxWeight given a list list of items)
    public static Solution knapSack(List<Item> inputItemList, Solution currentSolution, int pos) {

        //****************END OF EXPLORATION CONDITIONS****************//
        //end of list & out of capacity => return  currentSolution, no need to go further
        if (currentSolution.getCapacity() == 0 || pos < 0) {
            return currentSolution;
        }

        Item currentItem = inputItemList.get(pos);

        //if over-priced, just move to the pos to the left cause we can't keep this item,
        if (currentItem.getWeight() > currentSolution.getCapacity()) {
            return knapSack(inputItemList, currentSolution, pos - 1);
        }

        //****************EXPLORATION****************//
        //we create the solution tree in this section. N.b: The algorithm complexity is O(2^n) because we need to explore the whole solution tree
        //else switch on or off the item at position pos => we create temporary solutions and compare them
        //switch ON current item at position pos
        Solution tempRightSol = Solution.builder()
                .itemList(new ArrayList<>())
                .capacity(roundToOneDecimal(currentSolution.getCapacity() - currentItem.getWeight()))
                .value(roundToOneDecimal(currentSolution.getValue() + currentItem.getValue()))
                .build();
        //copy currentSolution itemList content
        tempRightSol.getItemList().addAll(currentSolution.getItemList());
        //add current item to tempRightSol itemList
        tempRightSol.getItemList().add(currentItem);

        //switch OFF current item at position pos (exclude it from original itemlist => no added value, no decreased capacity (no added weight)
        Solution tempLeftSol = Solution.builder()
                .itemList(new ArrayList<>())
                .capacity(currentSolution.getCapacity())
                .value(currentSolution.getValue())
                .build();
        //copy currentSolution itemList content
        tempLeftSol.getItemList().addAll(currentSolution.getItemList());
        //do not add current item to tempLeftSol itemList

        //recursive calls
        Solution tempRightResult = knapSack(inputItemList, tempRightSol, pos - 1);
        Solution tempLeftResult = knapSack(inputItemList, tempLeftSol, pos - 1);

        //return the itemList of the solution with max value
        return tempRightResult.getValue() > tempLeftResult.getValue() ? tempRightResult : tempLeftResult;

    }
}
