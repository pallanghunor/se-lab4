package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fire_Success_DecreasesCount() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        boolean result = store.fire(1);

        assertEquals(true, result);
        assertEquals(4, store.getTorpedoCount());
    }

    @Test
    void fire_MultipleTorpedoes_DecreasesCountByAll() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        boolean result = store.fire(3);

        assertEquals(true, result);
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_AllTorpedoes_EmptiesStore() {
        TorpedoStore store = new TorpedoStore(2, 0.0);

        store.fire(2);

        assertEquals(0, store.getTorpedoCount());
        assertEquals(true, store.isEmpty());
    }

    @Test
    void fire_Failure_KeepsCount() {
        TorpedoStore store = new TorpedoStore(5, 1.0);

        boolean result = store.fire(1);

        assertEquals(false, result);
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_ZeroTorpedoes_Throws() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_NegativeTorpedoes_Throws() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_MoreThanAvailable_Throws() {
        TorpedoStore store = new TorpedoStore(2, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_FromEmptyStore_Throws() {
        TorpedoStore store = new TorpedoStore(0, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
    }

    @Test
    void isEmpty_NonEmptyStore_False() {
        assertEquals(false, new TorpedoStore(1, 0.0).isEmpty());
    }

    @Test
    void isEmpty_EmptyStore_True() {
        assertEquals(true, new TorpedoStore(0, 0.0).isEmpty());
    }

    @Test
    void getTorpedoCount_ReturnsInitialCount() {
        assertEquals(7, new TorpedoStore(7, 0.0).getTorpedoCount());
    }
}
