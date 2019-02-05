package ru.job4j.tree;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CustomTreeTest {

    @Test
    public void when6ElFindLastThen6() {
        CustomTree<Integer> tree = new CustomTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        CustomTree<Integer> tree = new CustomTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void shouldReturnTrueWhenCheckIsBinary() {
        CustomTree<String> tree = new CustomTree<>("Zero");
        tree.add("Zero", "One");
        tree.add("Zero", "Two");
        tree.add("One", "Three");
        tree.add("One", "Four");
        tree.add("Two", "Five");

        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void shouldReturnFalseWhenCheckIsBinary() {
        CustomTree<String> tree = new CustomTree<>("Zero");
        tree.add("Zero", "One");
        tree.add("Zero", "Two");
        tree.add("One", "Three");
        tree.add("One", "Four");
        tree.add("One", "Five");
        tree.add("Two", "Six");

        assertThat(tree.isBinary(), is(false));
    }



}