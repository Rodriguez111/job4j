package ru.job4j.list;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class LoopSearchTest {
    private LoopSearch.Node first = new LoopSearch.Node(1);
    private LoopSearch.Node two = new LoopSearch.Node(2);
    private LoopSearch.Node third = new LoopSearch.Node(3);
    private LoopSearch.Node fourth = new LoopSearch.Node(4);
    private LoopSearch.Node fifth = new LoopSearch.Node(5);

   @Test
    public void shouldReturnTrueWhen5ClosesOn1() {
        first.next = two;
        two.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = first;

      boolean actual = LoopSearch.hasCycle(first);
      assertThat(actual, is(true));
    }

    @Test
    public void shouldReturnTrueWhen3ClosesOn2() {
        first.next = two;
        two.next = third;
        third.next = two;
        fourth.next = fifth;
        fifth.next = first;

        boolean actual = LoopSearch.hasCycle(first);
        assertThat(actual, is(true));
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoLoop() {
        first.next = two;
        two.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = null;

        boolean actual = LoopSearch.hasCycle(first);
        assertThat(actual, is(false));
    }

}