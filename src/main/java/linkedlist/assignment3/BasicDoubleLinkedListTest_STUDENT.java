package linkedlist.assignment3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedListTest_STUDENT {

    BasicDoubleLinkedList<Games> linkedgame;
    GamesComparator comparator;

    public Games game1 = new  Games("Pokemon", "Red and Blue", 1999);
    public Games game2 = new  Games("Metal Gear Solid", "MSG1", 1997);
    public Games game3 = new  Games("The Legend of Zelda", "Breath of The Wild", 2017);
    public Games game4 = new  Games("The Elder Scrolls", "Skyrim", 1995);
    public Games game5 = new Games("Overwatch", "OW2", 2022);


    @Before
    public void setUp() throws Exception {
        linkedgame = new BasicDoubleLinkedList<Games>();
        linkedgame.addToEnd(game2);
        linkedgame.addToEnd(game3);
        linkedgame.addToEnd(game4);
        comparator = new GamesComparator();
    }

    @After
    public void tearDown() throws Exception {
        linkedgame = null;
        comparator = null;
    }

    @Test
    public void testGetSize() {
        assertEquals(3, linkedgame.getSize());
    }

    @Test
    public void testAddToEnd() {
        assertEquals(game4, linkedgame.getLast());
        linkedgame.addToEnd(game5);
        assertEquals(game5, linkedgame.getLast());
    }

    @Test
    public void testAddToFront() {
        assertEquals(game2, linkedgame.getFirst());
        linkedgame.addToFront(game1);
        assertEquals(game1, linkedgame.getFirst());
    }

    @Test
    public void testGetFirst() {
        assertEquals(game2, linkedgame.getFirst());
        linkedgame.addToFront(game1);
        assertEquals(game1, linkedgame.getFirst());
    }

    @Test
    public void testGetLast() {
        assertEquals(game4, linkedgame.getLast());
        linkedgame.addToEnd(game5);
        assertEquals(game5, linkedgame.getLast());
    }

    @Test
    public void testToArrayList() {
        ArrayList<Games> list;
        linkedgame.addToFront(game1);
        linkedgame.addToEnd(game5);
        list = linkedgame.toArrayList();
        assertEquals(game1, list.get(0));
        assertEquals(game2, list.get(1));
        assertEquals(game3, list.get(2));
        assertEquals(game4, list.get(3));
        assertEquals(game5, list.get(4));
    }

    @Test
    public void testIteratorSuccessfulNext() {

        linkedgame.addToFront(game1);
        linkedgame.addToEnd(game5);
        ListIterator<Games> iteratorGames = linkedgame.iterator();
        assertEquals(true, iteratorGames.hasNext());
        assertEquals(game1, iteratorGames.next());
        assertEquals(game2, iteratorGames.next());
        assertEquals(game3, iteratorGames.next());
        assertEquals(game4, iteratorGames.next());
        assertEquals(true, iteratorGames.hasNext());
        assertEquals(game5, iteratorGames.next());
        assertEquals(false, iteratorGames.hasNext());
    }

    @Test
    public void testIteratorSuccessfulPrevious() {
        linkedgame.addToFront(game1);
        linkedgame.addToEnd(game5);
        ListIterator<Games> iteratorGames = linkedgame.iterator();
        assertEquals(true, iteratorGames.hasNext());
        assertEquals(game1, iteratorGames.next());
        assertEquals(game2, iteratorGames.next());
        assertEquals(game3, iteratorGames.next());
        assertEquals(game4, iteratorGames.next());
        assertEquals(game5, iteratorGames.next());
        assertEquals(true, iteratorGames.hasPrevious());
        assertEquals(game5, iteratorGames.previous());
        assertEquals(game4, iteratorGames.previous());
        assertEquals(game3, iteratorGames.previous());
        assertEquals(game2, iteratorGames.previous());
        assertEquals(game1, iteratorGames.previous());
    }

    @Test
    public void testIteratorNoSuchElementExceptionNext() {

        linkedgame.addToFront(game1);
        linkedgame.addToEnd(game5);
        ListIterator<Games> iteratorGames = linkedgame.iterator();
        assertEquals(true, iteratorGames.hasNext());
        assertEquals(game1, iteratorGames.next());
        assertEquals(game2, iteratorGames.next());
        assertEquals(game3, iteratorGames.next());
        assertEquals(game4, iteratorGames.next());
        assertEquals(true, iteratorGames.hasNext());
        assertEquals(game5, iteratorGames.next());
        assertEquals(false, iteratorGames.hasNext());

        try {
            iteratorGames.next();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }

    }

    @Test
    public void testIteratorNoSuchElementExceptionPrevious() {
        linkedgame.addToFront(game1);
        linkedgame.addToEnd(game5);
        ListIterator<Games> iteratorGames = linkedgame.iterator();
        assertEquals(true, iteratorGames.hasNext());
        assertEquals(game1, iteratorGames.next());
        assertEquals(game2, iteratorGames.next());
        assertEquals(game3, iteratorGames.next());
        assertEquals(game4, iteratorGames.next());
        assertEquals(game5, iteratorGames.next());
        assertEquals(true, iteratorGames.hasPrevious());
        assertEquals(game5, iteratorGames.previous());
        assertEquals(game4, iteratorGames.previous());
        assertEquals(game3, iteratorGames.previous());
        assertEquals(game2, iteratorGames.previous());
        assertEquals(game1, iteratorGames.previous());

        try {
            iteratorGames.previous();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }

    }

    @Test
    public void testIteratorUnsupportedOperationException() {
        linkedgame.addToFront(game1);
        ListIterator<Games> iteratorGames = linkedgame.iterator();
        assertEquals(true, iteratorGames.hasNext());
        assertEquals(game1, iteratorGames.next());
        assertEquals(game2, iteratorGames.next());

        try {
            iteratorGames.remove();
            assertTrue("Did not throw a UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw a UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }

    }

    @Test
    public void testRemove() {
        assertEquals(3, linkedgame.getSize());
        assertEquals(game2, linkedgame.getFirst());
        assertEquals(game4, linkedgame.getLast());
        linkedgame.remove(game2, comparator);
        assertEquals(2, linkedgame.getSize());
        assertEquals(game3, linkedgame.getFirst());
        assertEquals(game4, linkedgame.getLast());
        linkedgame.addToFront(game1);
        assertEquals(game1, linkedgame.getFirst());
        assertEquals(game4, linkedgame.getLast());
        linkedgame.remove(game4, comparator);
        assertEquals(2, linkedgame.getSize());
        assertEquals(game1, linkedgame.getFirst());
        assertEquals(game3, linkedgame.getLast());

    }

    @Test
    public void testRetrieveFirstElement() {
        assertEquals(3, linkedgame.getSize());
        assertEquals(game2, linkedgame.getFirst());
        assertEquals(game4, linkedgame.getLast());
        linkedgame.addToFront(game1);
        assertEquals(game1, linkedgame.getFirst());
        assertEquals(game1, linkedgame.retrieveFirstElement());
        assertEquals(game2, linkedgame.getFirst());
        assertEquals(3, linkedgame.getSize());
    }

    @Test
    public void testRetrieveLastElement() {
        assertEquals(3, linkedgame.getSize());
        assertEquals(game2, linkedgame.getFirst());
        assertEquals(game4, linkedgame.getLast());
        linkedgame.addToEnd(game5);
        assertEquals(game5, linkedgame.getLast());
        assertEquals(game5, linkedgame.retrieveLastElement());
        assertEquals(game4, linkedgame.getLast());
        assertEquals(3, linkedgame.getSize());
    }

    private class GamesComparator implements Comparator<Games> {

        @Override
        public int compare(Games first, Games second) {
            return first.toString().compareTo(second.toString());
        }
    }

    private class Games {
        String gameName;
        String title;
        int year;

        public Games(String bandName, String title, int year) {
            this.gameName = bandName;
            this.title = title;
            this.year = year;
        }

        public String getGameName() {
            return gameName;
        }

        public String getTitle() {
            return title;
        }

        public int getYear() {
            return year;
        }

        public String toString() {
            return (getGameName() + " " + getTitle() + " " + getYear());
        }

    }

}